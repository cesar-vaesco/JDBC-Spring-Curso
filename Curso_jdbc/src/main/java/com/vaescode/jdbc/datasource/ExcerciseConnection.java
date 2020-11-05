package com.vaescode.jdbc.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExcerciseConnection {

	private static final int NUM_CONNECTIONS = 100;

	public static void main(String[] args) throws SQLException {
		long startTime = System.currentTimeMillis();
		
		/*
		 * Velocidad de conexión
		 * 
		 *   1 = 480ms
		 *   5 = 1892ms
		 *  10 = 3348ms
		 *  20 = 7169ms
		 *  30 = 10906ms
		 *  50 = 14984ms
		 * 100 = 28898ms
		 * */
		for (int i = 0; i < NUM_CONNECTIONS; i++) {
			Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
			
			connection.close();
		}
		
		System.out.println("Total time: "+ (System.currentTimeMillis() - startTime)  + "ms");
	}
}
