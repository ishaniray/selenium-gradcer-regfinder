package com.cerner.selenium.demo;

import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		long startTime = System.currentTimeMillis();
		
		System.out.println("Please wait while we fetch information..." + System.lineSeparator());
		
		Map<String, List<String>> mcaGraduates = GradCeremonyUtils.getMCAGraduates();
		
		System.out.println(
				System.lineSeparator() + 
				"The following HIT MCA 2019 Graduates have registered for Graduation Ceremony 2020: " + 
				System.lineSeparator());
		
		for(String registrant : mcaGraduates.get("Registrants")) {
			System.out.println(registrant);
		}
		
		System.out.println(
				System.lineSeparator() + 
				"The following HIT MCA 2019 Graduates have not registered for Graduation Ceremony 2020: " + 
				System.lineSeparator());
		
		for(String registrant : mcaGraduates.get("Non-Registrants")) {
			System.out.println(registrant);
		}
		
		System.out.println(
				System.lineSeparator() + 
				"The following HIT MCA 2019 Graduates have contingently registered for Graduation Ceremony 2020: " + 
				System.lineSeparator());
		
		for(String registrant : mcaGraduates.get("Contingent Registrants")) {
			System.out.println(registrant);
		}
		
		int totalNumberOfGraduates = mcaGraduates.get("Registrants").size() + mcaGraduates.get("Non-Registrants").size() + mcaGraduates.get("Contingent Registrants").size();
		
		System.out.println(
				System.lineSeparator() + 
				"Total number of MCA 2019 Graduates: " + 
				totalNumberOfGraduates);
		
		long endTime = System.currentTimeMillis();
		
		double executionTimeInSeconds = (endTime - startTime) / 1000.0;
		
		System.out.println(System.lineSeparator() + "Process took " + executionTimeInSeconds + " seconds.");
	}
}
