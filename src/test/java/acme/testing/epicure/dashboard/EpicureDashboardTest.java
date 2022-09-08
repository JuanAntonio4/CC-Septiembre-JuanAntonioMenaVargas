/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.epicure.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;


public class EpicureDashboardTest extends TestHarness {
	
	private final String epicurePath = "/epicure/epicure-dashboard/show";
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(0)
	public void anonymousAccess() {		
		this.checkXpathNotExists("//*[@id='mainMenu']/ul[1]/li[2]/div/a[3][normalize-space() = 'Administrator']");
		
		this.navigate(this.epicurePath);
		this.checkPanicExists();
	}
	
	@Test
	@Order(1)
	public void checkDashboard() {
		this.signIn("epicure1", "epicure1");
		
		// check that an patron user has patron dashboard link on menu
		this.checkXpathContains("//*[@id=\"mainMenu\"]/ul[1]/li[2]/div/a[2]", "Epicure Dashboard");
		
		// check that an patron user can access to patron dashboard
		this.navigate(this.epicurePath);
		this.checkNotPanicExists();
		
		
	}
	
	@Test
	@Order(2)
	public void checkDashboardInputs() {
		
		this.signIn("epicure1", "epicure1");
		this.navigate(this.epicurePath);
		
		final Map<String, String> inputsMap = new HashMap<String, String>();
		
		// Total proposed, accepted & denied patronages
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[2]/div[1]/div/div/label/input", "2");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[2]/div[2]/div/div/label/input", "3");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[2]/div[3]/div/div/label/input", "2");
		
		//patronages
		// Proposed in EUR
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[3]/div[1]/div/div/label/input", "66.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[3]/div[2]/div/div/label/input", "20.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[3]/div[3]/div/div/label/input", "43.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[3]/div[4]/div/div/label/input", "23.0");
		
		
		
				
		
		// Acepted in EUR
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[4]/div[1]/div/div/label/input", "68.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[4]/div[2]/div/div/label/input", "50.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[4]/div[3]/div/div/label/input", "56.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[4]/div[4]/div/div/label/input", "8.48528137423857");
		
		
		
		// Denied in EUR
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[5]/div[1]/div/div/label/input", "80.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[5]/div[2]/div/div/label/input", "13.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[5]/div[3]/div/div/label/input", "46.5");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[5]/div[4]/div/div/label/input", "33.5");
		
		
		
		

		
		for(final Map.Entry<String, String> entry : inputsMap.entrySet()) {
			this.checkXpathInputValue(entry.getKey(), entry.getValue());
		}
		
	}
	
	// Ancillary methods ------------------------------------------------------ 
	
}
