package com.vaescode.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.vaescode.jdbc.models.Address;

@SpringBootApplication
public class CursoSpringJdbcApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(CursoSpringJdbcApplication.class);

	@Autowired
	private JdbcTemplate template;

	public void insertAddressess(List<Address> addresses) {

		int[] rowsImpacted = template.batchUpdate(
				"insert into address(street, number,postal_code, employee_id) values(?,?,?,?)",
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Address address = addresses.get(i);
						ps.setString(1, address.getStreet());
						ps.setString(2, address.getNumber());
						ps.setInt(3, address.getPostal_code());
						ps.setInt(4, address.getEmployeeId());

					}

					@Override
					public int getBatchSize() {
						return addresses.size();
					}
				});
		for (int row : rowsImpacted) {
			log.info("Rows impacted {}", row);

		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		insertAddressess(Arrays.asList(new Address("Miguel Hidalgo", "105 - b", 25000, 6),
				                       new Address("Miguel Hidalgo", "105 - b", 25000, 7)));
	}

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringJdbcApplication.class, args);

	}

}
