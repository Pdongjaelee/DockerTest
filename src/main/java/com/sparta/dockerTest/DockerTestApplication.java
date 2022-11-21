package com.sparta.dockerTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DockerTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(DockerTestApplication.class, args);
	}

}
