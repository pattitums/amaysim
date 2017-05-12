package com.amaysim;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
	private final static String PROMO_FILE = "promo.properties";
	private final static String DEAL_FILE = "deals.properties";
	
	public static String fetchCode(String fileName, String code){
		Properties prop = new Properties();
		InputStream input = null;
		String out = null;

		try {
			input = Util.class.getClassLoader().getResourceAsStream(fileName);
    		if(input==null){
    	            System.out.println("Sorry, unable to find " + fileName);
    		    return out;
    		}

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			out = prop.getProperty(code);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return out;
	}
	
	public static String fetchPromoCode(String promoCode) {
		return fetchCode(PROMO_FILE, promoCode);
	}
	
	public static String fetchDeals(String productCode) {
		return fetchCode(DEAL_FILE, productCode);
	}
}
