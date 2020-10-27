package com.vaescode.jdbc;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.h2.tools.RunScript;

public class JdbcConnections {

	public static void main(String[] args) {

		/*Conexi�n a base de datos enbebida en Memoria
		 *H2 es un sistema administrador de bases de datos relacionales programado en Java. 
		 *Puede ser incorporado en aplicaciones Java o ejecutarse de modo cliente-servidor. */
		
		try {
			System.out.println("Conectando...");
			Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
			System.out.println("Conectado..");
			
			System.out.println("Ejecutando script..");
			RunScript.execute(connection, new FileReader("src/main/resources/squema.sql"));
			System.out.println("Script ejecutado..");
			
			
			/*
			 * PreparedStatement esta clase permite preparar una sentencia sql 
			 * */
			PreparedStatement statement = connection.prepareStatement("insert into person (name, last_name, nickname) values (?,?,?)");

			/*Agregar atributos a la sentencia statement*/
			statement.setString(1, "C�sar");
			statement.setString(2, "Vargas");
			statement.setString(3, "@vargas_dev");
			
			int rows = statement.executeUpdate();
			
			System.out.println("Columnas impactadas: " + rows);
			
			/*Establece el par�metro designado en SQL*/
			statement.executeUpdate();
			
			/*
			 * Libera la base de datos de este objeto Statement y los recursos JDBC inmediatamente en lugar 
			 * de esperar a que esto suceda cuando se cierre autom�ticamente. 
			 * Por lo general, es una buena pr�ctica liberar los recursos tan pronto como haya terminado 
			 * con ellos para evitar inmovilizar los recursos de la base de datos.
			 * */
			statement.close();
			
			System.out.println("Cierre de conexi�n...");
			
			connection.close();
			System.out.println("Conexi�n cerrada!");
			
		} catch (SQLException  | FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
}
