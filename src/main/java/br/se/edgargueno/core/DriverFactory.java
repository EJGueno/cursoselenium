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
			
//			if(driver == null) {
//				switch (Propriedades.browser) {
//					case FIREFOX: driver = new FirefoxDriver(); break;
//					case CHROME: driver = new ChromeDriver(); break;
//				}
//				driver.manage().window().setSize(new Dimension(1200, 765));			
					
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
