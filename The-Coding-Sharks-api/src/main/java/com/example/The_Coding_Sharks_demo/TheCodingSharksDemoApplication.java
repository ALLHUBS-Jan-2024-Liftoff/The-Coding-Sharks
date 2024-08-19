package com.example.The_Coding_Sharks_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TheCodingSharksDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheCodingSharksDemoApplication.class, args);
	}

	// To prevent CORS issue with local development
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Allow all endpoints
						.allowedOrigins("http://localhost:5173") // Allow requests from your front-end origin
						.allowedOrigins("http://localhost:8080")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these methods
						.allowedHeaders("*") // Allow all headers
						.allowCredentials(true); // Allow credentials
			}
		};
	}

}
