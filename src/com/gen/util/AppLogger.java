package com.gen.util;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class AppLogger {
	private static Logger logger = null;

	private AppLogger() {
		logger = Logger.getRootLogger();
    	BasicConfigurator.configure();
    	
    	LoadProperties properties = new LoadProperties();
    	switch (properties.getPropertyForValue("logLevel")) {
		case "debug":
					logger.setLevel(Level.DEBUG);
					break;
		case "info":
						logger.setLevel(Level.INFO);
					break;
		case "warn":
					logger.setLevel(Level.WARN);
					break;
		case "error":
					logger.setLevel(Level.ERROR);
					break;
		default:
					logger.setLevel(Level.FATAL);
					break;
		}
	}

	public static Logger getLogger() {
		if (logger == null) {
			new AppLogger();
		}
		return logger;
	}
	
}
