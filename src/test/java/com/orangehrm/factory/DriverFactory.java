package com.orangehrm.factory;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static final Logger logger = LogManager.getLogger(DriverFactory.class);
			
    // Thread-safe WebDriver instance
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
        // Prevent instantiation
    }

    /**
     * Initialize WebDriver based on browser name.
     *
     * @param browser Browser name (chrome, firefox, edge)
     */
    public static void initDriver(String browser) {

        if (browser == null || browser.isBlank()) {
            browser = "chrome";
        }

        switch (browser.toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");

                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                driver.set(new FirefoxDriver(firefoxOptions));
                driver.get().manage().window().maximize();

                break;

            case "edge":

                WebDriverManager.edgedriver().setup();

                EdgeOptions edgeOptions = new EdgeOptions();

                driver.set(new EdgeDriver(edgeOptions));
                driver.get().manage().window().maximize();

                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported Browser : " + browser);
        }

        driver.get().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get().manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));

        driver.get().manage().timeouts()
                .scriptTimeout(Duration.ofSeconds(30));
    }

    /**
     * Returns current WebDriver instance.
     *
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit browser and remove ThreadLocal instance.
     */
    public static void quitDriver() {
    	logger.info("driver.get() : "+driver.get().getTitle());
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}