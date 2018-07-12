package com.form3.paymentsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableReactiveMongoRepositories
public class PaymentsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApiApplication.class, args);
	}
}
