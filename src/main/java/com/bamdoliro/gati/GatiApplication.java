package com.bamdoliro.gati;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class GatiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatiApplication.class, args);
	}

}
