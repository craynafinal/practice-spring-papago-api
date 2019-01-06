package com.chaosdepot.services.papagoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PapagoApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PapagoApiApplication.class, args);
	}
}
