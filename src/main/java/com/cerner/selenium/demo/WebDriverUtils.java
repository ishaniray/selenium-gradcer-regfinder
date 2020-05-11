package com.cerner.selenium.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtils {
	
	public static WebDriver getChromeDriver() {
		return new ChromeDriver();
	}
	
	public static WebDriver getHeadlessChromeDriver() {
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		
		return new ChromeDriver(chromeOptions);
	}
}
