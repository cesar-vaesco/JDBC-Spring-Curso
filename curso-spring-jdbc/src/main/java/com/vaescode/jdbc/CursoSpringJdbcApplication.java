package com.vaescode.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CursoSpringJdbcApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(CursoSpringJdbcApplication.class);

	@Autowired
	private JdbcTemplate template;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		try {
		int rows = template.update("insert into address (street, number, postal_code, employee_id ) values(?,?,?,?)",
				"Isidro Varela", "165 - A", 12500, 16);

		log.info("Rows impacted:{}", rows);
		}catch(DataAccessException ex){
			
			log.info("Exception recived {}", ex.getClass());
			log.info("Caused by {} ",ex.getCause());
			log.info("Message {}", ex.getMessage());

			}
		}
	

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringJdbcApplication.class, args);

	}

}
