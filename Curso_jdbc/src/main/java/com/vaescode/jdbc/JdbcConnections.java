package com.vaescode.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnections {

	public static void main(String[] args) {

		/*Conexi�n a base de datos enbebida en Memoria
		 *H2 es un sistema administrador de bases de datos relacionales programado en Java. 
		 *Puede ser incorporado en aplicaciones Java o ejecutarse de modo cliente-servidor. */
		
		try {
			System.out.println("Conectando...");
			Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
			System.out.println("Conectado..");
			System.out.println("Cierre de conexi�n...");
			
			connection.close();
			System.out.println("Conexi�n cerrada!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
