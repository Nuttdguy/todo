package com.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.todo.notification.FlashMessage;

//  #0005.01  //==	add Security Configurations; extend WebSecurityConfigurerAdapter
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	//  #0005.02  //== configure areas/folders to ignore i.e. not restrict access to
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
	
	//  #0005.03  //==	configure http protocol access permits
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().hasRole("USER")
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.successHandler(loginSuccessHandler())
				.failureHandler(loginFailureHandler())
				.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/login");
	}
	
	//  #0005.04  //==	create method to handle request / response when login succeeds
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> response.sendRedirect("/");
    }

    //  #0005.05  //==  create method to handle request / response redirects when login fails
    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
            response.sendRedirect("/login");
        };
    }
	
}
