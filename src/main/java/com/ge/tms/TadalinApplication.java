package com.ge.tms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
		
/**
 * Spring Boot Application Starting point containing the main method.
 * @author Nitin K.
 */
@SpringBootApplication
public class TadalinApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TadalinApplication.class);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TadalinApplication.class, args);
	}
}