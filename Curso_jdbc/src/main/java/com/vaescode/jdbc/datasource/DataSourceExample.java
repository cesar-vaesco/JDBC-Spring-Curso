package com.vaescode.jdbc.datasource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;

public class DataSourceExample {

	public static void main(String[] args) throws SQLException, FileNotFoundException {
		JdbcDataSource datasource = new JdbcDataSource();
		 datasource.setUrl("jdbc:h2:~/test");
		
		 //~try-with-resources~
		 try(Connection connection = datasource.getConnection();){
		 
		 RunScript.execute(connection, new FileReader("src/main/resources/squema.sql"));
		 }
		 //Siempre se debe de asegurar de que se cierren las conexiones
	
	}
}
