package com.gen.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
	 
	private FileReader reader;
	private Properties properties;
	
	public LoadProperties() {
		try {
			reader = new FileReader("C:/My Home/E/JavaPrac/LibraryManagementSystem/WebContent/Properties/Application.properties");
			
			properties = new Properties();
			properties.load(reader); 
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}  
	}
	
	public String getPropertyForValue(String propertyValue) {
		return properties.getProperty(propertyValue);
	}
}
