package com.jimicloud.dgs.dgsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = SimpleShowsDataRepository.class)
public class DgsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DgsDemoApplication.class, args);
	}

}
