package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationShowTest extends TestHarness {
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/show.csv", numLinesToSkip = 1)
	@Order(0)
	public void checkListing(final int recordIndex, final String spamWords,final String spamThreshold, final String defaultCurr, final String acceptedCurr) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Configurations");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, spamWords);
		super.checkColumnHasValue(recordIndex, 1, spamThreshold);
		super.checkColumnHasValue(recordIndex, 2, defaultCurr);
		super.checkColumnHasValue(recordIndex, 3, acceptedCurr);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/show.csv", numLinesToSkip = 1)
	@Order(1)
	public void checkShow(final int recordIndex, final String spamWords,final String spamThreshold, final String defaultCurr, final String acceptedCurr) {
		super.signIn("administrator", "administrator");
		
		super.navigate("/administrator/configuration/show");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("spamWords", spamWords);
		super.checkInputBoxHasValue("spamThreshold", spamThreshold);
		super.checkInputBoxHasValue("defaultCurr", defaultCurr);
		super.checkInputBoxHasValue("acceptedCurr", acceptedCurr);
		
		super.signOut();
	}
	
	@Test
    @Order(8)
    public void hackingTest() {

        super.navigate("/administrator/configuration/list");
        super.checkPanicExists();

        super.signIn("chef1", "chef1");
        super.navigate("/administrator/configuration/list");
        super.checkPanicExists();
        super.signOut();


    }
	
	
}
