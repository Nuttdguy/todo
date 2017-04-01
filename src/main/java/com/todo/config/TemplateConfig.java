package com.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

//  #0004.01  //==  add template configuration
@Configuration
public class TemplateConfig {

	//  #0004.02  //==  add bean for Spring Resource Template Resolver for resolving view templates
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		final SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		
		templateResolver.setCacheable(false);
		templateResolver.setPrefix("classpath:/templates/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}

	//  #0004.03  //==  add bean for Spring Template Engine; add view templates to Spring template engine
	@Bean
	public SpringTemplateEngine templateEngine() {
		final SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		
		springTemplateEngine.addTemplateResolver(templateResolver());
		springTemplateEngine.addDialect( new SpringSecurityDialect());
		return springTemplateEngine;
	}
	
	//  #0004.04  //==  add bean to resolve thymeleaf templates; adds the SpringTemplateEngine to the View Resolver
	@Bean
	public ThymeleafViewResolver viewResolver() {
		final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
}
