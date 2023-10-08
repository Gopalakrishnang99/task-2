package com.gopal.task.two.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
public class TaskletJobConfig {

    @Bean
    public Tasklet userStatusUpdateTasklet(JdbcTemplate template) {
        return (contribution, chunkContext) -> {
            template.update("UPDATE user_info SET status = 'INACTIVE' " +
                    "WHERE logged_in_at < NOW() AT TIME ZONE 'utc' - INTERVAL '30 MINUTES'");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Job userStatusUpdateJob(JobRepository jobRepository){
        return new JobBuilder("userStatusUpdateJob",jobRepository)
                .start(userStatusUpdateTaskletStep(null,null))
                .build();
    }

    @Bean
    public Step userStatusUpdateTaskletStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("userStatusUpdateTaskletStep",jobRepository)
                .tasklet(userStatusUpdateTasklet(null),transactionManager)
                .allowStartIfComplete(true)
                .build();
    }
}
