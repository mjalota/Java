package com.munish.lms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.munish.lms.datasource.FileDataSource;
import com.munish.lms.myexception.BBException;


public  class Utils {
	private static Logger logger = Logger.getLogger(FileDataSource.class);

	public static long daysBetween(Date one, Date two) { 
		long difference = CalendarUtil.getDaysBetween(one, two) ; 
		return Math.abs(difference); 
	}

	public static Properties  loadProperties(String filename ) throws BBException{
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = ClassLoader.getSystemResourceAsStream(filename);
			prop.load(input);
		} catch (IOException ex) {
			logger.info("Exception occured during load properties = " + ex.getMessage());
			throw new BBException("Exception occured during load properties = " + ex.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.info("Exception occured during load properties = " + e.getMessage());
					throw new BBException("Exception occured during load properties = " + e.getMessage());	
				}
			}
		}
		return prop;
	}

}
