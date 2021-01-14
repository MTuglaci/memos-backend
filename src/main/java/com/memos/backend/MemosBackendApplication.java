package com.memos.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MemosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemosBackendApplication.class, args);
	}

}
