package com.ecom.user;

import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class UserMicroserviceApplication {
	@Autowired
	private Environment environment;
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(MongoTemplate mongoClient) {
		return args -> {
			String dbName = mongoClient.getDb().getName();
			System.out.println("Connected DB : " + dbName);

			System.out.println(environment.getProperty("spring.mongodb.database"));
			System.out.println(environment.getProperty("spring.mongodb.uri"));

		};
	}

}
