package com.vaescode.jdbc.datasource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;

public class ExcerciseConnection {

	private static final int NUM_CONNECTIONS = 100;
	/*
	 * Velocidad de conexión        Usando pool de conexiones
	 * 
	 *   1 = 480ms                    1 = 259ms     
	 *   5 = 1892ms                   5 = 260ms
	 *  10 = 3348ms                  10 = 224ms
	 *  20 = 7169ms                  20 = 259ms
	 *  30 = 10906ms                 30 = 260ms
	 *  50 = 14984ms                 50 = 260ms
	 * 100 = 28898ms                100 = 269ms
	 * */
	public static void main(String[] args) throws SQLException {
		
		JdbcConnectionPool connectionPool = JdbcConnectionPool.create("jdbc:h2:~/test","","");
		long startTime = System.currentTimeMillis();
		
		
		for (int i = 1; i <= NUM_CONNECTIONS; i++) {
			Connection connection =  connectionPool.getConnection();

			System.out.println(i);
			connection.close();
		}
		
		System.out.println("Total time: "+ (System.currentTimeMillis() - startTime)  + "ms");
	}
}
