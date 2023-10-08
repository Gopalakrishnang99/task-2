package com.gopal.task.two.config.job;

import com.gopal.task.two.common.UserItemProcessor;
import com.gopal.task.two.jdbc.UserRowMapper;
import com.gopal.task.two.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JdbcJobConfig {

    @Bean
    public Job userStatusJob(JobRepository jobRepository) {
        return new JobBuilder("userStatusUpdateJdbcJob", jobRepository)
                .start(statusUpdateStep(null, null))
                .build();
    }

    @Bean
    public JdbcPagingItemReader<User> userItemReader(DataSource dataSource, PagingQueryProvider pagingQueryProvider) {
        return new JdbcPagingItemReaderBuilder<User>()
                .name("userReader")
                .dataSource(dataSource)
                .queryProvider(pagingQueryProvider)
                .pageSize(2)
                .rowMapper(new UserRowMapper())
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean provider = new
                SqlPagingQueryProviderFactoryBean();
        provider.setDataSource(dataSource);
        provider.setSelectClause("select *");
        provider.setFromClause("from user_info");
        provider.setSortKey("user_id");
        return provider;
    }

    @Bean
    public JdbcBatchItemWriter<User> updateUserStatusWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<User>()
                .dataSource(dataSource)
                .sql("UPDATE user_info SET status = 'INACTIVE' " +
                        "WHERE user_id = :userId")
                .beanMapped()
                .assertUpdates(true)
                .build();
    }

    @Bean
    public Step statusUpdateStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("statusUpdateStep", jobRepository)
                .<User,User>chunk(10,transactionManager)
                .reader(userItemReader(null,null))
                .writer(updateUserStatusWriter(null))
                .processor(new UserItemProcessor())
                .allowStartIfComplete(true)
                .build();
    }
}
