package com.vaescode.jdbc.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCpExample {

	public static void main(String[] args) throws SQLException {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:h2:~/test");
		config.setUsername("");
		config.setPassword("");
		config.setMaximumPoolSize(5);
		
		HikariDataSource connectionPoolHikari = new HikariDataSource(config);
		
		
		Connection connection1 = connectionPoolHikari.getConnection();
		Connection connection2= connectionPoolHikari.getConnection();
		Connection connection3 = connectionPoolHikari.getConnection();
		Connection connection4 = connectionPoolHikari.getConnection();
		Connection connection5 = connectionPoolHikari.getConnection();
		
		connection1.close();
		connection2.close();
		connection3.close();
		connection4.close();
		connection5.close();
		
		connectionPoolHikari.close();
	}
}
