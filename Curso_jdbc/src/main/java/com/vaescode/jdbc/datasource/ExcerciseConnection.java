package com.vaescode.jdbc.datasource;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class ExcerciseConnection {

	private static final int NUM_CONNECTIONS = 10000000;
	/*
	 * Velocidad de conexión     pool H2         PoolHikariCP        dbcp2 
	 *  usando DriverManager
	 *   1 = 480ms             -(H2) 259ms       -(HCP) 14ms      -(dbcp2) 580ms
	 *   5 = 1892ms            -(H2) 260ms       -(HCP) 15ms      -(dbcp2) 530ms
	 *  10 = 3348ms            -(H2) 224ms       -(HCP) 19ms      -(dbcp2) 500ms 
	 *  20 = 7169ms            -(H2) 259ms       -(HCP) 18ms      -(dbcp2) 763ms
	 *  30 = 10906ms           -(H2) 260ms       -(HCP) 16ms      -(dbcp2) 595ms
	 *  50 = 14984ms           -(H2) 260ms       -(HCP) 17ms      -(dbcp2) 675ms
	 * 100 = 28898ms           -(H2) 269ms       -(HCP) 17ms      -(dbcp2) 519ms
	 * 10000 = 				   -(H2) 628ms       -(HCP) 114ms     -(dbcp2) 575ms
	 * 100000 =			       -(H2) 1936ms      -(HCP) 541ms     -(dbcp2) 1237ms
	 * 10000000 =			   -(H2) 61715ms     -(HCP) 44986ms   -(dbcp2) 
	 * */
	public static void main(String[] args) throws SQLException {
		
		//JdbcConnectionPool connectionPool = JdbcConnectionPool.create("jdbc:h2:~/test","","");
		//HikariConfig config = new HikariConfig();
		//config.setJdbcUrl("jdbc:h2:~/test");
		//config.setUsername("");
		//config.setPassword("");
		//HikariDataSource connectionPoolHikari = new HikariDataSource(config);
		BasicDataSource dbcpDataSource = new BasicDataSource();
		dbcpDataSource.setUrl("jdbc:h2:~/test");
		dbcpDataSource.setUsername("");
		dbcpDataSource.setPassword("");
		
		long startTime = System.currentTimeMillis();
		
		
		for (int i = 1; i <= NUM_CONNECTIONS; i++) {
			Connection connection = dbcpDataSource.getConnection();

			System.out.println(i);
			connection.close();
		}
		
		System.out.println("Total time: "+ (System.currentTimeMillis() - startTime)  + "ms");
		//Cerrando pool de conexiones
		dbcpDataSource.close();
	}
}
