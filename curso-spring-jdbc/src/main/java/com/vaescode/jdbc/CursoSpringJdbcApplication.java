package com.vaescode.jdbc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CursoSpringJdbcApplication {

	
	private static final Logger log = LoggerFactory.getLogger(CursoSpringJdbcApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CursoSpringJdbcApplication.class, args);
		
		DataSource dataSource = context.getBean(DataSource.class);
		log.info("DataSource implementation {}", dataSource.getClass().getName());

	}

}
