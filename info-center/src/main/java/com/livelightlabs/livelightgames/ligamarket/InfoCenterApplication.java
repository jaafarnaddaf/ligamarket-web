package com.livelightlabs.livelightgames.ligamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("com.livelightlabs.livelightgames.ligamarket.entity")
@EnableJpaRepositories
@EnableJpaAuditing
public class InfoCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoCenterApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}