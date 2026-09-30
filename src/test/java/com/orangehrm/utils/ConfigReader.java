package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static final Properties properties = new Properties();

	static {

		try {

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