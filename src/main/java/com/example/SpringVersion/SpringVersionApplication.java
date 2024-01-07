package com.example.SpringVersion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringVersionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringVersionApplication.class, args);
	}

}
