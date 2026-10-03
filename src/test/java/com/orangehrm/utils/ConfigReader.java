package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

	private static final Logger logger = LogManager.getLogger(ConfigReader.class);
	private static final Properties properties = new Properties();

	static {

		try {
			
			String projectEnv = System.getenv("PROJECT_NAME");
			String reportDir = System.getenv("REPORT_DIR");
			String testEnv = System.getProperty("testEnv");
			String browser = System.getProperty("browser");
			
			logger.info("project environmnet : "+projectEnv);
			logger.info("reportDir : "+reportDir);
			logger.info("TestEnv: "+testEnv);
			logger.info("Browser: "+browser);

			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/config/config.properties");

			properties.load(fis);
			fis.close();

		} catch (IOException e) {

			throw new RuntimeException("Unable to load config.properties", e);
		}
	}

	private ConfigReader() {
	}

	public static String getProperty(String key) {

		String value = properties.getProperty(key);

		if (value == null) {
			throw new RuntimeException("Key not found in config.properties : " + key);
		}

		return value.trim();
	}

	public static String getBrowser() {
		return getProperty("browser");
	}

	public static String getApplicationUrl() {
		return getProperty("url");
	}

	public static String getUsername() {
		return getProperty("username");
	}

	public static String getPassword() {
		return getProperty("password");
	}

	public static int getImplicitWait() {
		return Integer.parseInt(getProperty("implicitWait"));
	}

	public static int getExplicitWait() {
		return Integer.parseInt(getProperty("explicitWait"));
	}

	public static boolean isHeadless() {
		return Boolean.parseBoolean(getProperty("headless"));
	}
}