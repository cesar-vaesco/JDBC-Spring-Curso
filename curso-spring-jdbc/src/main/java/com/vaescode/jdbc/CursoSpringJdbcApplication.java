package com.vaescode.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CursoSpringJdbcApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(CursoSpringJdbcApplication.class);

	@Autowired
	private JdbcTemplate template;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		/*
		 * Listar por nombre List<String> names =
		 * template.queryForList("select name from employee", String.class);
		 */
		// Listar usando where
		// List<String> names = template.queryForList("select name from employee where
		// age >= 18 ", String.class);
		// Pasando parametros al query
		List<String> names = template.queryForList("select name from employee where age >= ?", new Object[] {35},
				String.class);

		for (String name : names) {

			log.info("Employee name {}", name);

		}
	}

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringJdbcApplication.class, args);

	}

}
