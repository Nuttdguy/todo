package com.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan( basePackages="com.todo")
public class Application {

	//  #0001.01	  //== load application into SpringApplication Servlet
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
