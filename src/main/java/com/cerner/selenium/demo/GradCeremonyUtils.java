package com.cerner.selenium.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class GradCeremonyUtils {
	
	private static String baseUrl = "http://136.232.2.202:8083/GraduationCeremony/OnlineRegistration1.aspx";
	private static long mcaFirstRoll = 30316010001L;
	private static long mcaLastRoll = 30316010057L;
	
	private static void setChromeDriverProperty() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
	}
	
	public static Map<String, List<String>> getMCAGraduates() throws InterruptedException {
		
		Map<String, List<String>> mcaGraduates = new HashMap<>();
		
		List<String> registrants = new ArrayList<>(); // people who've registered
		List<String> nonRegistrants = new ArrayList<>(); // people who haven't registered
		List<String> contingentRegistrants = new ArrayList<>(); // people who've filled the registration form, but not paid yet
		
		setChromeDriverProperty();
		
		WebDriver driver = WebDriverUtils.getHeadlessChromeDriver();
		
		long currentRoll = mcaFirstRoll;
		
		while(currentRoll <= mcaLastRoll) {
			
			driver.get(baseUrl);
			
			driver.findElement(By.id("txtSpecifyUnivRegdNo")).sendKeys(String.valueOf(currentRoll));
			driver.findElement(By.id("btnVerify")).click();
			
			Thread.sleep(500);
			
			try {
				driver.findElement(By.id("btnContinueToPay1"));
				contingentRegistrants.add(driver.findElement(By.id("lblName2")).getAttribute("textContent"));

			} catch (NoSuchElementException nsee1) {
				
				try {
					registrants.add(driver.findElement(By.id("lblName2")).getAttribute("textContent"));
					
				} catch (NoSuchElementException nsee2) {
					
					String nonRegistrant = driver.findElement(By.id("txtName")).getAttribute("value");
					
					if(nonRegistrant != null && !nonRegistrant.isEmpty()) {
						nonRegistrants.add(nonRegistrant);
					}
				}
			}
			
			currentRoll += 1;
		}
		
		driver.close();
		
		mcaGraduates.put("Registrants", registrants);
		mcaGraduates.put("Non-Registrants", nonRegistrants);
		mcaGraduates.put("Contingent Registrants", contingentRegistrants);
		
		return mcaGraduates;
	}
}
