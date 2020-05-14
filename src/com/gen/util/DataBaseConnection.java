package com.gen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private Connection connection;
	private LoadProperties properties;
	
	public Connection newConnection() {
		try {
			properties = new LoadProperties();
			Class.forName(properties.getPropertyForValue("driverName"));  
			  
			connection = DriverManager.getConnection(properties.getPropertyForValue("connectionString"),
					properties.getPropertyForValue("userId"), properties.getPropertyForValue("password")); 
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection() {
		try {
			connection.close();
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
