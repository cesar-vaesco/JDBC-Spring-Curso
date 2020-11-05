package com.vaescode.jdbc.datasource;


import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ExcerciseConnection {

	private static final int NUM_CONNECTIONS = 10000000;
	/*
	 * Velocidad de conexión        Usando pool de conexiones de H2     PoolHikariCP        
	 *  usando DriverManager
	 *   1 = 480ms                    1 = 259ms                         1  = 14ms  
	 *   5 = 1892ms                   5 = 260ms                         5  = 15ms
	 *  10 = 3348ms                  10 = 224ms                         10 = 19ms
	 *  20 = 7169ms                  20 = 259ms                         20 = 18ms
	 *  30 = 10906ms                 30 = 260ms                         30 = 16ms
	 *  50 = 14984ms                 50 = 260ms                         50 = 17ms
	 * 100 = 28898ms                100 = 269ms                        100 = 17ms
	 * 							  10000 = 628ms                      10000 = 114ms
	 * 							 100000 = 1936ms                    100000 = 541ms
	 * 						   10000000 = 61715ms                 10000000 = 44986ms
	 * */
	public static void main(String[] args) throws SQLException {
		
		//JdbcConnectionPool connectionPool = JdbcConnectionPool.create("jdbc:h2:~/test","","");
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:h2:~/test");
		config.setUsername("");
		config.setPassword("");
		
		HikariDataSource connectionPoolHikari = new HikariDataSource(config);
		long startTime = System.currentTimeMillis();
		
		
		for (int i = 1; i <= NUM_CONNECTIONS; i++) {
			Connection connection = connectionPoolHikari.getConnection();

			System.out.println(i);
			connection.close();
		}
		
		System.out.println("Total time: "+ (System.currentTimeMillis() - startTime)  + "ms");
		//Cerrando pool de conexiones
		connectionPoolHikari.close();
	}
}
