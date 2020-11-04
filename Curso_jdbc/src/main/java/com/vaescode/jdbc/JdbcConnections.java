package com.vaescode.jdbc;

/*Las clasees que tienen que ver con jdbc se encuentran en los paquetes 
 * java.sql y javax.sql, esas clases ya vienen en javaSE por lo que no necesitan ninguna dependencia adicional
 * Puedo utilizar clases del provedor de las bases de datos, por ejemplo: import org.h2.tools.RunScript;
 * */
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
			/*DriverMAnager nos permite crear una conexi�n a una base de datos
			 * 
			 *-- Necesita una URL y puede recibir configuraciones adicionales como el usuario y contrase�a
			 *   */
			Connection  connection = DriverManager.getConnection("jdbc:h2:~/test");
			System.out.println("Conectado..");
			/**
			 * RunScript nos permite ejecutar una serie de secuencias en SQL,
			 * en el ejemolo se borra la tabla que existe y se vuelve a crear
			 * */
			System.out.println("Ejecutando script..");
			RunScript.execute( connection, new FileReader("src/main/resources/squema.sql"));
			System.out.println("Script ejecutado..");
			/**
			 * A trav�s del objeto connection crearemos uno de tipo Statement
			 * PreparedStatement y CalleableStatement
			 * */
			
			PreparedStatement statement =  connection.prepareStatement("insert into person (name, last_name, nickname) values (?,?,?)");

			/**
			 * Prepared Statement permite ejecutarlo multiples veces con diferentes parametros
			 * */
			
			/*Agregar atributos a la sentencia statement*/
			statement.setString(1, "C�sar");
			statement.setString(2, "Vargas");
			statement.setString(3, "@vargas_dev");
			
			/**
			 *Devuelve el n�mero de registros impactados
			 * 
			 * */
			int rows = statement.executeUpdate();
			
			System.out.println("Columnas impactadas: " + rows);
			
			statement.setString(1, "Vanessa");
			statement.setString(2,"Cortez");
			statement.setString(3, "@Vane");
			
			/**
			 *Puede devolver el n�mero de registros impactados o un rsultado set
			 * */
			 boolean execute = statement.execute();
			 System.out.println("Es una inserci�n: " + (execute == false));
			 rows = statement.getUpdateCount();
			 
		     System.out.println("Columnas impactadas: " + rows);
		     /**
		      * Los statement se deben de cerrar
		      * */
		     statement.close();
		     
		     /*Generando consulta con PreparedStatement
		      *ResultSet va a permitir apuntar a las filas donde se encuentren los datos de la busqueda
		      *  */
			 PreparedStatement statementQuery = connection.prepareStatement("SELECT * FROM person");

			 boolean execute2 = statementQuery.execute();
			 System.out.println("Is resultSet: " + (execute2));
			 ResultSet rs = statementQuery.getResultSet();
			 
			 //ResultSet rs = statementQuery.executeQuery();
			 
			 /**
			  * El m�todo next nos mueve el cursor y nos dice si hay valores o no
			  * */
			 while(rs.next()) {
				 //Los m�todos getXXX nos devuelven los valores de la columna
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
			 * de esperar a que esto suceda cuando se cierre autom�ticamente. 
			 * Por lo general, es una buena pr�ctica liberar los recursos tan pronto como haya terminado 
			 * con ellos para evitar inmovilizar los recursos de la base de datos.
			 * */
		    deleteStatement.close();
			statement.close();
			/**
			 * Las conexiones se deben cerrar siempre 
			 * */
			System.out.println("Cierre de conexi�n...");
			
			 connection.close();
			System.out.println("Conexi�n cerrada!");
			/**
			 * En caso de error se producira una SQLException
			 * */
		} catch (SQLException  | FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
}
