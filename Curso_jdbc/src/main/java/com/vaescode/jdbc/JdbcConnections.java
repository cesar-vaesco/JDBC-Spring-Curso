package com.vaescode.jdbc;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.tools.RunScript;

public class JdbcConnections {

	public static void main(String[] args) {

		
		
		try {
			System.out.println("Conectando...");
			Connection  connection = DriverManager.getConnection("jdbc:h2:~/test");
			System.out.println("Conectado..");
			
			System.out.println("Ejecutando script..");
			RunScript.execute( connection, new FileReader("src/main/resources/squema.sql"));
			System.out.println("Script ejecutado..");
			
			
			PreparedStatement statement =  connection.prepareStatement("insert into person (name, last_name, nickname) values (?,?,?)");

			
			
			/*Agregar atributos a la sentencia statement*/
			statement.setString(1, "César");
			statement.setString(2, "Vargas");
			statement.setString(3, "@vargas_dev");
			
			int rows = statement.executeUpdate();
			
			System.out.println("Columnas impactadas: " + rows);
			
			statement.setString(1, "Vanessa");
			statement.setString(2,"Cortez");
			statement.setString(3, "@Vane");
			
			/*Establece el parámetro designado en SQL*/
		     rows = statement.executeUpdate();
			
		     System.out.println("Columnas impactadas: " + rows);
		     statement.close();
		     
		     /*Generando consulta con PreparedStatement
		      *ResultSet va a permitir apuntar a las filas donde se encuentren los datos de la busqueda
		      *  */
			 PreparedStatement statementQuery = connection.prepareStatement("SELECT * FROM person");

			 ResultSet rs = statementQuery.executeQuery();
			 
			 while(rs.next()) {
				 
				 System.out.printf("\nId[%d] \tName [%s] \tLastname [%s] \tNickName [%s]",
						 			rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			 }
		     statementQuery.close();
		     
		     /*Preparando la sentencia que va a permitir borra registro de la base de datos*/
		     PreparedStatement deleteStatement =  connection.prepareStatement("DELETE FROM person");
		     
		     /*Columnas impactadas con la sentencia deleteStatement*/
		    int rowsDelete = deleteStatement.executeUpdate();
		    
		    System.out.println("\nRows deleted: " + rowsDelete);
		    
		     
			/*
			 * Libera la base de datos de este objeto Statement y los recursos JDBC inmediatamente en lugar 
			 * de esperar a que esto suceda cuando se cierre automáticamente. 
			 * Por lo general, es una buena práctica liberar los recursos tan pronto como haya terminado 
			 * con ellos para evitar inmovilizar los recursos de la base de datos.
			 * */
		    deleteStatement.close();
			statement.close();
			
			System.out.println("Cierre de conexión...");
			
			 connection.close();
			System.out.println("Conexión cerrada!");
			
		} catch (SQLException  | FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
}
