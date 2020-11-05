package com.vaescode.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.vaescode.jdbc.mappers.EmployeeRowMapper;
import com.vaescode.jdbc.models.Employee;

@SpringBootApplication
public class CursoSpringJdbcApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(CursoSpringJdbcApplication.class);

	@Autowired
	private JdbcTemplate template;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Employee> employees = template.query("select * from employee", (rs, rowNum) -> {

			Employee employee = new Employee();
			employee.setId(rs.getInt(1));
			employee.setName(rs.getString(2));
			employee.setLastname(rs.getString(3));
			employee.setAge(rs.getInt(4));
			employee.setSalary(rs.getDouble(5));
			return employee;
		});

		for (Employee employee : employees) {
			log.info("Id:{}, Name:{}, LastName:{}, Age:{}, Salary:{}", employee.getId(), employee.getName(),
					employee.getLastname(), employee.getAge(), employee.getSalary());
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringJdbcApplication.class, args);

	}

}
