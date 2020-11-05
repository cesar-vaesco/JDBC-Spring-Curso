package com.vaescode.jdbc.mappers;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vaescode.jdbc.models.Employee;


public class EmployeeRowMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt(1));
		employee.setName(rs.getString(2));
		employee.setLastname(rs.getString(3));
		employee.setAge(rs.getInt(4));
		employee.setSalary(rs.getDouble(5));
		return employee;
	}

	
//Integer id, String name, String lastname, Integer age, Double salary
}
