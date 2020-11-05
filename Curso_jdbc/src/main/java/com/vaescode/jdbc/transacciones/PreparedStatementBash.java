package com.vaescode.jdbc.transacciones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.h2.tools.RunScript;

import com.github.javafaker.Faker;

public class PreparedStatementBash {

	public static void main(String[] args) throws SQLException, FileNotFoundException {
		
		try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO person(name, last_name, nickname) values(?, ?,? )");) {
			System.out.println("Conectado..");

			System.out.println("Ejecutando script..");
			/* Creación de la tabla */
			RunScript.execute(connection, new FileReader("src/main/resources/squema.sql"));
			
			try {
				
				Faker faker = new Faker();
				connection.setAutoCommit(false);
				for(int  i=0; i<100; i++) {
					//Datos a persistir
					statement.setString(1, faker.name().firstName());
					statement.setString(2, faker.name().lastName());
					statement.setString(3, faker.superhero().name());
					//Ejecutar la declaración
					statement.addBatch();
				}
			
				connection.commit();
			int [] executeBatch = statement.executeBatch();
			
			for (int rowImpacted : executeBatch) {
				System.out.println("Columnas impactadas " +rowImpacted);
			}
			
		System.out.println(executeBatch.length);
			
			}catch (SQLException e) {
				System.out.printf("Error..." , e.getMessage());
			}
			
			System.out.println("Record persisted...");
			//Prepara la declaración sql y la ejecuta
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
