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

package acme.testing.epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class EpicureFineDishCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/finedishPositivo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(6)
	public void positiveTest(final int recordIndex, final String status, final String code, final String request, final String budget, final String startsAt, final String finishesAt, final String link) {

		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "List Fine Dishes");
		super.checkListingExists();
		super.clickOnButton("Create finedish");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startsAt", startsAt);
		super.fillInputBoxIn("finishesAt", finishesAt);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Create finedish");

		
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
	
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/finedishNegativo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(7)
	public void negativeTest(final int recordIndex, final String status, final String code, final String request, final String budget, final String startsAt, final String finishesAt, final String link) {
		//Misma estructura que en el positivo
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "List Fine Dishes");
		super.checkListingExists();
		super.clickOnButton("Create finedish");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startsAt", startsAt);
		super.fillInputBoxIn("finishesAt", finishesAt);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Create finedish");

		super.checkErrorsExist();

		super.signOut();

	}

	@Test
    @Order(8)
    public void hackingTest() {

        super.navigate("/epicure/fine-dish/create");
        super.checkPanicExists();

        super.signIn("chef1", "chef1");
        super.navigate("/epicure/fine-dish/create");
        super.checkPanicExists();
        super.signOut();


    }
		

}
