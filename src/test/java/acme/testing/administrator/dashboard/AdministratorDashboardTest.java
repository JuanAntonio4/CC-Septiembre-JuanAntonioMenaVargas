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

package acme.testing.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;


public class AdministratorDashboardTest extends TestHarness {
	
	private final String adminPath = "/administrator/dashboard/show";
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(0)
	public void anonymousAccess() {		
		this.checkXpathNotExists("//*[@id='mainMenu']/ul[1]/li[2]/div/a[3][normalize-space() = 'Administrator']");
		
		this.navigate(this.adminPath);
		this.checkPanicExists();
	}
	
	@Test
	@Order(1)
	public void checkDashboard() {
		this.signIn("administrator", "administrator");
		
		// check that an admin user has admin dashboard link on menu
		this.checkXpathContains("//*[@id=\"mainMenu\"]/ul[1]/li[2]/div/a[6]", "Dashboard");
		
		// check that an admin user can access to admin dashboard
		this.navigate(this.adminPath);
		this.checkNotPanicExists();
		
		// check admin dashboard input exists
		this.checkXpathExists("//*[@id=\"totals\"]/div[1]/div[1]/div/div/label/input");
	}
	
	@Test
	@Order(2)
	public void checkDashboardInputs() {
		
		this.signIn("administrator", "administrator");
		this.navigate(this.adminPath);
		
		final Map<String, String> inputsMap = new HashMap<String, String>();
		
		// Total items & finedish
		inputsMap.put("//*[@id=\"totals\"]/div[1]/div[1]/div/div/label/input", "7");
		inputsMap.put("//*[@id=\"totals\"]/div[1]/div[2]/div/div/label/input", "7");
		
		// Total proposed, accepted & denied finedish
		inputsMap.put("//*[@id=\"totals\"]/div[2]/div[1]/div/div/label/input", "7");
		inputsMap.put("//*[@id=\"totals\"]/div[2]/div[2]/div/div/label/input", "8");
		inputsMap.put("//*[@id=\"totals\"]/div[2]/div[3]/div/div/label/input", "7");
		
		// Budgets
		// Proposed
		inputsMap.put("//*[@id=\"budgets\"]/div[1]/div[1]/div/div/label/input", "87.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[1]/div[2]/div/div/label/input", "20.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[1]/div[3]/div/div/label/input", "53.42857142857143");
		inputsMap.put("//*[@id=\"budgets\"]/div[1]/div[4]/div/div/label/input", "23.249314231712255");
		
		// Acepted
		inputsMap.put("//*[@id=\"budgets\"]/div[2]/div[1]/div/div/label/input", "68.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[2]/div[2]/div/div/label/input", "21.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[2]/div[3]/div/div/label/input", "45.375");
		inputsMap.put("//*[@id=\"budgets\"]/div[2]/div[4]/div/div/label/input", "13.228165972650933");
		
		// Denied
		inputsMap.put("//*[@id=\"budgets\"]/div[3]/div[1]/div/div/label/input", "80.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[3]/div[2]/div/div/label/input", "13.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[3]/div[3]/div/div/label/input", "45.857142857142854");
		inputsMap.put("//*[@id=\"budgets\"]/div[3]/div[4]/div/div/label/input", "25.06807059319297");
		
		
		// Total items 
		// ingredient -> currency eur
		inputsMap.put("//*[@id=\"items\"]/div[1]/div[1]/div/div/label/input", "17.0");
		inputsMap.put("//*[@id=\"items\"]/div[1]/div[2]/div/div/label/input", "10.0");
		inputsMap.put("//*[@id=\"items\"]/div[1]/div[3]/div/div/label/input", "11.428571428571429");
		inputsMap.put("//*[@id=\"items\"]/div[1]/div[4]/div/div/label/input", "2.498979383505129");
		
		
		// kitchen utensil -> currency eur
		inputsMap.put("//*[@id=\"items\"]/div[2]/div[1]/div/div/label/input", "15.0");
		inputsMap.put("//*[@id=\"items\"]/div[2]/div[2]/div/div/label/input", "10.0");
		inputsMap.put("//*[@id=\"items\"]/div[2]/div[3]/div/div/label/input", "14.285714285714286");
		inputsMap.put("//*[@id=\"items\"]/div[2]/div[4]/div/div/label/input", "1.749635530559413");
		
		
		//Control Check
		// Ratio Pimpam CookingItem
		inputsMap.put("//*[@id=\"items\"]/div[1]/div/div/div/label/input", "17.0");
				
		// Pimpam -> currency eur
		inputsMap.put("//*[@id=\"items\"]/div/div[1]/div/div/label/input", "17.0");
		inputsMap.put("//*[@id=\"items\"]/div/div[2]/div/div/label/input", "10.0");
		inputsMap.put("//*[@id=\"items\"]/div/div[3]/div/div/label/input", "11.428571428571429");
		inputsMap.put("//*[@id=\"items\"]/div/div[4]/div/div/label/input", "2.498979383505129");
				

		
		for(final Map.Entry<String, String> entry : inputsMap.entrySet()) {
			this.checkXpathInputValue(entry.getKey(), entry.getValue());
		}
		
	}
	
	// Ancillary methods ------------------------------------------------------ 
	
}
