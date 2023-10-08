package com.gopal.task.two.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JdbcJobConfig {

    @Bean
    public Job job(JobRepository jobRepository) {
        return new JobBuilder("customJob", jobRepository)
                .start(step1(null,null))
                .build();
    }

    @Bean
    public JdbcPagingItemReader

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .tasklet((stepContribution, chuckContext) -> {
                    System.out.println("Inside tasklet");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
