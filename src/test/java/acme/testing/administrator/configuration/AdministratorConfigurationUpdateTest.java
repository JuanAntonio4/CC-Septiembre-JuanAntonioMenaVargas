package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String spamWords, final String spamThreshold, 
		final String defaultCurr, final String acceptedCurr ) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Configurations");
		
        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
		super.fillInputBoxIn("spamWords", spamWords);
		super.fillInputBoxIn("spamThreshold", spamThreshold);
		super.fillInputBoxIn("defaultCurr", defaultCurr);
		super.fillInputBoxIn("acceptedCurr", acceptedCurr);
		
		
		
		super.clickOnSubmit("Update configuration");	
				
		
		super.clickOnMenu("Administrator", "Configurations");
		super.checkListingExists();

		super.checkColumnHasValue(recordIndex, 0, spamWords);
		super.checkColumnHasValue(recordIndex, 1, spamThreshold);
		super.checkColumnHasValue(recordIndex, 2, defaultCurr);
		super.checkColumnHasValue(recordIndex, 3, acceptedCurr);
	
		super.signOut();	
		
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/updateNegativo.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String spamWords, final String spamThreshold,  final String defaultCurr, final String acceptedCurr) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Configurations");
		
        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.fillInputBoxIn("spamWords", spamWords);
		super.fillInputBoxIn("spamThreshold", spamThreshold);
		super.fillInputBoxIn("defaultCurr", defaultCurr);
		super.fillInputBoxIn("acceptedCurr", acceptedCurr);
		
		super.clickOnSubmit("Update configuration");	
				
		super.checkErrorsExist();

		super.signOut();	
		
	}
	
	@Test
    @Order(8)
    public void hackingTest() {

        super.navigate("/administrator/configuration/update");
        super.checkPanicExists();

        super.signIn("chef1", "chef1");
        super.navigate("/administrator/configuration/update");
        super.checkPanicExists();
        super.signOut();


    }

}
