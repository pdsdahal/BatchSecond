package com.lambdatest.baselayer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.lambdatest.util.ReadConfigFile;

public class BrowserDriver {

	private static WebDriver webDriver;

	public static void launchDriver() {

		Properties properties = ReadConfigFile.getProperty("config");

		String browserName = properties.get("browserName").toString();
		String browserURL = properties.get("browserURL").toString();

		switch (browserName) {

		case "chrome":
			webDriver = lunchChrome();
			break;

		case "safari":
			webDriver = lunchSafari();
			break;

		case "firefox":
			webDriver = lunchFirefox();

			break;

		case "ie":
			webDriver = lunchIE();
			break;

		}

		webDriver.get(browserURL);
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		webDriver.manage().deleteAllCookies();
	}

	public static void start() {
		if (webDriver == null) {
			launchDriver();
		}
	}
	
	public static void close() {
		if (webDriver != null) {
			webDriver.quit();
			webDriver = null;
		}
	}
	
	public static WebDriver getWebDriver() {
		return webDriver;
	}

	private static WebDriver lunchIE() {

		return new InternetExplorerDriver();
	}

	private static WebDriver lunchFirefox() {

		String baseDirectoryProject = System.getProperty("user.dir");
		String geckoDriverPath = baseDirectoryProject + "\\src\\test\\resources\\driver\\geckodriver.exe";

		System.setProperty("webdriver.gecko.driver", geckoDriverPath);

		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addArguments("start-maximized");
		firefoxOptions.addArguments("--remote-allow-origins=*");
		
		return new FirefoxDriver(firefoxOptions);

	}

	private static WebDriver lunchSafari() {

		return new SafariDriver();
	}

	private static WebDriver lunchChrome() {

		String baseDirectoryProject = System.getProperty("user.dir");
		String chromeDriverPath = baseDirectoryProject + "\\src\\test\\resources\\driver\\chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("--remote-allow-origins=*");
		
		System.out.println("Driver is launching......");

		return new ChromeDriver(chromeOptions);

	}

}
