package acme.testing.chef.fineDish;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefFineDishdenyTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dish/finedishCreateprueba.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void acceptedcreateTest(final int recordIndex, final String status, final String code,final String request,final String budget ,final String startsAt, final String finishesAt, final String link){
		
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
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List Fine Dishes");
		
		super.sortListing(2, "desc");
        super.clickOnListingRecord(recordIndex);
       
        super.checkFormExists();
        
        super.clickOnSubmit("Publish finedish");
        super.signOut();
        
        
       
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dish/finedishAccepted.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void acceptedTest(final int recordIndex, final String status, final String code,final String request,final String budget ,final String startsAt, final String finishesAt, final String link) {
		 super.signIn("administrator", "administrator");
	        super.clickOnMenu("Chef", "List Own Fine Dishes");
	        
	        super.checkListingExists();
			super.sortListing(2, "desc");
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.clickOnSubmit("Accept fine dish");
			
			
			
			 super.clickOnMenu("Chef", "List Own Fine Dishes");
		        
		        super.checkListingExists();
				super.sortListing(2, "desc");
				
				super.clickOnListingRecord(recordIndex);
				super.checkFormExists();
			
			super.checkInputBoxHasValue("status", status);
			super.checkInputBoxHasValue("code", code);
			super.checkInputBoxHasValue("request", request);
			super.checkInputBoxHasValue("budget", budget);
			super.checkInputBoxHasValue("startsAt", startsAt);
			super.checkInputBoxHasValue("finishesAt", finishesAt);
			super.checkInputBoxHasValue("link", link);
			
			super.signOut();
		
	}
	
	
	
	
	
	
	
		

}
