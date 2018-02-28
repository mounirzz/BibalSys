package com.bibal.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesManager {

	private static Properties properties = new Properties();
	private static InputStream inputStream = null;
	private static OutputStream outputStream = null;
	
	public static int getDelai(String typeOeuvre) {
		try {
			inputStream = new FileInputStream("src/main/resources/bibalConfig.properties");
			properties.load(inputStream);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return Integer.parseInt(properties.getProperty(typeOeuvre));

	}
	
	public static void setDelai(String typeOeuvre, int delai){
		try {
			outputStream = new FileOutputStream("src/main/resources/bibalConfig.properties");
			properties.setProperty(typeOeuvre,String.valueOf(delai));
			properties.store(outputStream, null);
		} catch (FileNotFoundException e) {		
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if (outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
			
		}
	}

}
