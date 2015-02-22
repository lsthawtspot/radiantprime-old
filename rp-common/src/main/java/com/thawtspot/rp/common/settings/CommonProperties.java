package com.thawtspot.rp.common.settings;

import java.io.InputStream;
import java.util.Properties;

public class CommonProperties {

	private static Properties props = new Properties();
	
	static {
		
		InputStream stream = CommonProperties.class.getResourceAsStream("/common.properties");
		
		try {
			props.load(stream);
		}
		catch(Exception e) {
			System.out.println("Unable to read file: "+e);
			throw new RuntimeException("Unable to read properties file.");
		}
		finally {
				try { stream.close(); } catch(Exception e) {}
		}		
	}
	
	public static String getString(String paramName) {
		return props.getProperty(paramName);
	}
	
	public static Integer getInteger(String paramName) {
		try {
			return Integer.parseInt(props.getProperty(paramName));
		}
		catch (Exception e) {
			System.out.println("Problem parsing : "+e);
			return null;
		}
	}
	
}
