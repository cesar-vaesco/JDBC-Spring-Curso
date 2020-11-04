package com.vaescode.jdbc.transacciones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.h2.tools.RunScript;

public class VaescodeTransacciones {

	public static void main(String[] args) throws SQLException, FileNotFoundException {

		/* Conexi�n */
		/*
		 * La conexi�n esta declarada dentro de un ~try-with-resources~
		 * 
		 * La instrucci�n -with-resources es una instrucci�n que declara uno o varios
		 * recursos. Un recurso es un objeto que debe cerrarse una vez finalizado el
		 * programa con �l. La instrucci�n -with-resources garantiza que cada recurso se
		 * cierra al final de la instrucci�n. Cualquier objeto que implemente , que
		 * incluye todos los objetos que implementan, se puede utilizar como
		 * recurso.trytrytryjava.lang.AutoCloseablejava.io.Closeable
		 */
		try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO person(name, last_name, nickname) values(?, ?,? )");) {
			System.out.println("Conectado..");

			System.out.println("Ejecutando script..");
			/* Creaci�n de la tabla */
			RunScript.execute(connection, new FileReader("src/main/resources/squema.sql"));
			//Configuraci�n automatica desactivada
			connection.setAutoCommit(false);
			Savepoint savepoint = null;

			try {
				
			//Datos a persistir
			statement.setString(1, "C�sar");
			statement.setString(2, "Vargas");
			statement.setString(3, "Chicharo");
			//Ejecutar la declaraci�n
			statement.executeUpdate();
			
			savepoint = connection.setSavepoint("vaescodeSavePonit");

			//statement.setString(1, null);
			statement.setString(1, "Veronica");
			statement.setString(2, "Cortez");
			statement.setString(3, "Vitus");

			statement.executeUpdate();
			
			savepoint = connection.setSavepoint("vaescodeSavePonit");
			
			statement.setString(1, "Gloria");
			statement.setString(2, null);
			statement.setString(3, "Glo-Glo");

			statement.executeUpdate();
			//Hace que todos los cambios realizados desde la confirmaci�n / reversi�n anterior sean permanentes 
			//y libera los bloqueos de la base de datos que actualmente tiene este objeto de conexi�n.
			connection.commit();
			
			}catch (SQLException e) {
				//Una operaci�n de reversi�n deshace todos los cambios realizados por la transacci�n actual, es decir,
				//si se llama a un m�todo rollBack() de la interfaz Connection, todas las modificaciones se revierten 
				//hasta la �ltima confirmaci�n
				if (savepoint == null) {
					connection.rollback();
				}else {
					connection.rollback(savepoint);
				}
				
				System.out.println("Rolling back because " + e.getMessage());
			}finally {
				connection.setAutoCommit(true);
			}
			
			System.out.println("Record persisted...");
			//Prepara la declaraci�n sql y la ejecuta
			PreparedStatement result = connection.prepareStatement("SELECT * FROM person");
			ResultSet rs = result.executeQuery();

			//Muestra los datos persistidos
			while (rs.next()) {
				System.out.printf("\nId[%d] Name [%s] Lastname [%s] NickName [%s]", rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4));
			}

		}

	}
}
