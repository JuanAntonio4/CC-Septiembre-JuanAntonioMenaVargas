package acme.testing.epicure.fineDish;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/finedishCreateprueba.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(3)
	public void Create(final int recordIndex, final String status, final String code, final String request, final String budget, final String startsAt, final String finishesAt, final String link) {

		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "List Fine Dishes");
		super.clickOnButton("Create finedish");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startsAt", startsAt);
		super.fillInputBoxIn("finishesAt", finishesAt);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Create finedish");

		
		
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/finedishPositivoupdate.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(4)
	public void positiveTest(final int recordIndex, final String status, final String code,final String request,final String budget ,final String startsAt, final String finishesAt, final String link) {
		
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List Fine Dishes");
		
		super.sortListing(1, "asc");
        super.clickOnListingRecord(recordIndex);
       
        super.checkFormExists();
    	super.clickOnButton("Update finedish");	
        super.fillInputBoxIn("code", code);
        super.fillInputBoxIn("request",request);
		super.fillInputBoxIn("budget",budget);
		super.fillInputBoxIn("startsAt",startsAt);
		super.fillInputBoxIn("finishesAt",finishesAt);
		super.fillInputBoxIn("link",link);
		
		super.clickOnSubmit("Update finedish");	
				
		super.clickOnMenu("Epicure", "List Fine Dishes");
		super.checkListingExists();

		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.signOut();	
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/finedishNegativoupdate.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(5)
	public void negativeTest(final int recordIndex, final String status, final String code, final String request,final String budget ,final String startsAt, final String finishesAt, final String link) {
		
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List Fine Dishes");
		super.sortListing(1, "asc");
        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.clickOnButton("Update finedish");	
        super.fillInputBoxIn("code", code);
        super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget",budget);
		super.fillInputBoxIn("startsAt",startsAt);
		super.fillInputBoxIn("finishesAt",finishesAt);
		super.fillInputBoxIn("link",link);
		
		super.clickOnSubmit("Update finedish");	
				
		super.checkErrorsExist();

		super.signOut();	
		
	}
	
	
	

}
