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

import com.vaescode.jdbc.mappers.EmployeeRowMapper;
import com.vaescode.jdbc.models.Employee;

@SpringBootApplication
public class CursoSpringJdbcApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(CursoSpringJdbcApplication.class);

	@Autowired
	private JdbcTemplate template;

	

	@Override
	public void run(ApplicationArguments args) throws Exception {
	List<Employee>employees = template.query("select * from employee", new EmployeeRowMapper());
	
	for (Employee employee : employees) {
		log.info("Id:{}, Name:{}, LastName:{}, Age:{}, Salary:{}", employee.getId(), employee.getName(), employee.getLastname(), employee.getAge(), employee.getSalary());
	}
    

	}

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringJdbcApplication.class, args);

	}

}
