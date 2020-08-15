package com.sanjayautomation.recipebook.baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;

import com.sanjayautomation.recipebook.utilities.WebEventListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseSetup {

	public static Properties properties;
	public static InputStream inputStream = null;
	public static WebDriver driver;
	public static EventFiringWebDriver eDriver;
	public static WebEventListener eListener;
	public static Logger log;
	
	public BaseSetup() {
		
		log = LogManager.getLogger(this.getClass().getName());
		
		try {
			properties = new Properties();
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/sanjayautomation/recipebook/config/config.properties");
			properties.load(inputStream);
		}catch (IOException e) {
			System.out.println("Unable to load config.properties");
			log.error("Unable to load config.properties");
			e.printStackTrace();
		}
	}
	
	public static void initialize() {
		String browserName = properties.getProperty("browser").toLowerCase();
		
		if(browserName.equals("chrome")) {
			System.getProperty("webDriver.chrome.driver", "/Users/sanjaygurung/Selenium/Downloads/chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/sanjaygurung/Selenium/Downloads/geckodriver");
			driver = new FirefoxDriver();
		} else {
			System.out.println("Browser in config.properties is not valid. The value should be either chrome, firefox or IE. Eg: browser = chrome");
			log.error("Browser in config.properties is not valid. The value should be either chrome, firefox or IE. Eg: browser = chrome");
		}
		
		eDriver = new EventFiringWebDriver(driver);
		eListener = new WebEventListener();
		eDriver.register(eListener);

		
		driver = eDriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(properties.getProperty("url"));
	}

}
