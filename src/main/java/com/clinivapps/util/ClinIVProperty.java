package com.clinivapps.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClinIVProperty {
	
	private static ClinIVProperty instance = new ClinIVProperty();
	private Properties properties;
	
	private ClinIVProperty()
	{
		InputStream is = this.getClass().getResourceAsStream("Configuration.properties");
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ClinIVProperty getInstance()
	{
		return instance;
	}
	
	public String getProperties(String key)
	{
		return properties.getProperty(key);
	}

}
