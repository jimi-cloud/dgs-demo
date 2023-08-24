package com.jimicloud.dgs.dgsdemo;

import com.jimicloud.dgs.dgsdemo.repo.Review;
import com.jimicloud.dgs.dgsdemo.repo.Show;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@Slf4j
public class DgsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DgsDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(final ShowsDataRepository showsDataRepository) {
		return args -> {
			showsDataRepository.deleteAll();
			Show show = Show.builder()
					.title("Sons of Anarchy")
					.reviews(List.of(new Review(5)))
					.build();
			log.info(showsDataRepository.save(show).toString());
		};
	}
}