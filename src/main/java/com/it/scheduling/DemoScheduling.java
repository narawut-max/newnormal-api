package com.it.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoScheduling {
	public static void main(String[] args) {
		SpringApplication.run(DemoScheduling.class, args);
	}

}
