package com.feedback.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.feedback.application.GestionFeedbackApplication;
@SpringBootApplication

public class GestionFeedbackApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestionFeedbackApplication.class, args);
	
}
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
		
	}

}