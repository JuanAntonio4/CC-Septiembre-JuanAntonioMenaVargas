package acme.testing.chef.memorandum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandumTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/memorandum.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void testChefMemorandumList(final int recordIndex, final String status, final String code,final String budget, 
		final String seqNumber, final String instantiationMoment, final String report, final String link2) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "List Own Fine Dishes");
		super.checkListingExists();
		
        super.clickOnListingRecord(recordIndex);
        
        super.clickOnButton("Memorandums");
        
        super.checkColumnHasValue(recordIndex, 0, seqNumber);
		super.checkColumnHasValue(recordIndex, 1, instantiationMoment);
		super.checkColumnHasValue(recordIndex, 2, report);
		super.checkColumnHasValue(recordIndex, 3, link2);
		
        super.clickOnListingRecord(recordIndex);

        super.checkInputBoxHasValue("seqNumber", seqNumber);
        super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
        super.checkInputBoxHasValue("report", report);

        
		super.signOut();
	}

}