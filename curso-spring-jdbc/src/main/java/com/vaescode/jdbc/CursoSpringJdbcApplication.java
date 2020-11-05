package com.vaescode.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
		KeyHolder holder = new GeneratedKeyHolder();
		int rowsImpacted = template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(
						"insert into address(street, number,postal_code, employee_id) values(?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, "Avenida Siempre Viva");
				statement.setString(2, "20a");
				statement.setInt(3, 1200);
				statement.setInt(4, 9);
				return statement;
			}
		}, holder);
			//Conocer las llaves(ID) que se generan en la insercción
		log.info("Rows impacted {}", rowsImpacted);
		log.info("Generated key {}", holder.getKey().intValue());
	}

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringJdbcApplication.class, args);
	}

}
