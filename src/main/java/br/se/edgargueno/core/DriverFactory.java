package br.se.edgargueno.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	
	private static WebDriver driver;
	
	private DriverFactory() {}
	
	public static WebDriver getDriver() {
		
		if(driver == null) {
			System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
		}
		return driver;
	}

	public static void killDriver() {
		
		if(driver != null){
			driver.quit();
			driver = null;
		}
	}
}
