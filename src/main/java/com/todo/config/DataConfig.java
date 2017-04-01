package com.todo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

//  #0007.01  //==	Configure database and connection
@Configuration
@EnableJpaRepositories( basePackages= {"com.todo.model"})
@PropertySource("application.properties")  // create application.properties file and add configuration within the file
public class DataConfig {

	//  #0007.02  //==  utilize environment context
	@Autowired
	private Environment env;
	
	//  #0007.03  //== 	create Entity Manager Factory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		
		//  #0007.04  //==  choose the vender adapter to use 
		HibernateJpaVendorAdapter venderAdapter = new HibernateJpaVendorAdapter();
		
		//  #0007.05  //==	Set database datasource
		factory.setDataSource(dataSource());
		
		//  #0007.07 	//==	Set Vender adapter to use
		factory.setJpaVendorAdapter(venderAdapter);
		
		//  #0007.08  //== 	Set packages to scan properties
		factory.setPackagesToScan(env.getProperty("todo.entity.package"));
		
		//  #0007.09  //==	Set hibernate properties
		factory.setJpaProperties(getHibernateProperties());
		
		return factory;
		
	}

	//  #0007.06  //==  create method to wire datasource properties
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		
		ds.setDriverClassName(env.getProperty("todo.db.driver"));
		ds.setUrl(env.getProperty("todo.db.url"));
		ds.setUsername(env.getProperty("todo.db.username"));
		ds.setPassword(env.getProperty("todo.db.password"));
		return ds;
		
	}
	
	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.put("hibernate.implicit_naming_strategy", env.getProperty("hibernate.implicit_naming_strategy"));
		prop.put("hibernate.format_sql",  env.getProperty("hibernate.format_sql"));
		prop.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		prop.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbmddl.auto"));
		return prop;
	}
	
}
