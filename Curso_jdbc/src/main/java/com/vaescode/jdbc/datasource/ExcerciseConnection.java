package com.vaescode.jdbc.datasource;


import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ExcerciseConnection {

	private static final int NUM_CONNECTIONS = 1;
	/*
	 * Velocidad de conexión     pool H2         PoolHikariCP        dbcp2            c3po
	 *  usando DriverManager
	 *   1 = 480ms             -(H2) 259ms       -(HCP) 14ms      -(dbcp2) 580ms    -(c3po) 535ms
	 *   5 = 1892ms            -(H2) 260ms       -(HCP) 15ms      -(dbcp2) 530ms    -(c3po) 399ms
	 *  10 = 3348ms            -(H2) 224ms       -(HCP) 19ms      -(dbcp2) 500ms    -(c3po) 297ms
	 *  20 = 7169ms            -(H2) 259ms       -(HCP) 18ms      -(dbcp2) 763ms    -(c3po) 280ms
	 *  30 = 10906ms           -(H2) 260ms       -(HCP) 16ms      -(dbcp2) 595ms    -(c3po) 301ms
	 *  50 = 14984ms           -(H2) 260ms       -(HCP) 17ms      -(dbcp2) 675ms    -(c3po) 331ms
	 * 100 = 28898ms           -(H2) 269ms       -(HCP) 17ms      -(dbcp2) 519ms    -(c3po) 315ms
	 * 10000 = 				   -(H2) 628ms       -(HCP) 114ms     -(dbcp2) 575ms    -(c3po) 540ms
	 * 100000 =			       -(H2) 1936ms      -(HCP) 541ms     -(dbcp2) 1237ms   -(c3po) 1601ms
	 * 10000000 =			   -(H2) 61715ms     -(HCP) 44986ms   -(dbcp2) 57722ms  -(c3po) 101446ms
	 * */
	public static void main(String[] args) throws SQLException {
		
		//JdbcConnectionPool connectionPool = JdbcConnectionPool.create("jdbc:h2:~/test","","");
		//HikariConfig config = new HikariConfig();
		//config.setJdbcUrl("jdbc:h2:~/test");
		//config.setUsername("");
		//config.setPassword("");
		//HikariDataSource connectionPoolHikari = new HikariDataSource(config);
		//BasicDataSource dbcpDataSource = new BasicDataSource();
		//dbcpDataSource.setUrl("jdbc:h2:~/test");
		//dbcpDataSource.setUsername("");
		//dbcpDataSource.setPassword("");
		ComboPooledDataSource c3poConnectionPool = new ComboPooledDataSource();
		c3poConnectionPool .setJdbcUrl("jdbc:h2:~/test");
		c3poConnectionPool .setUser("");
		c3poConnectionPool .setPassword("");
		long startTime = System.currentTimeMillis();
		
		
		for (int i = 1; i <= NUM_CONNECTIONS; i++) {
			Connection connection =c3poConnectionPool.getConnection();

			System.out.println(i);
			connection.close();
		}
		
		System.out.println("Total time: "+ (System.currentTimeMillis() - startTime)  + "ms");
		//Cerrando pool de conexiones
		c3poConnectionPool .close();
	}
}
