package com.gopal.task.two;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Task2Application {

	public static void main(String[] args) {

		SpringApplication.run(Task2Application.class, args);
		/*template.query("select * from user_info where user_id = 1",r->{
			return r.getString("username");
		});*/
	}

}
