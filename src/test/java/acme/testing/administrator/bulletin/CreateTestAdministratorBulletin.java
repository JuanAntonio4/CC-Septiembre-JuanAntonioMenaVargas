package acme.testing.administrator.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CreateTestAdministratorBulletin extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/bulletin/bulletinPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String heading, final String instationMoment, 
		final String pieceOfText,final String critic , final String link, final String confirm) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "List Bulletins");
		
		super.checkListingExists();
		super.clickOnButton("Create new one");
		super.fillInputBoxIn("heading",heading);
		super.fillInputBoxIn("pieceOfText",pieceOfText);
		super.fillInputBoxIn("critic",critic);
		super.fillInputBoxIn("link",link);
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create new one");
		
		super.checkListingExists();
		

		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 2, pieceOfText);
		super.checkColumnHasValue(recordIndex, 3, critic);
		super.checkColumnHasValue(recordIndex, 4, link);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/bulletin/bulletinNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex,final String heading, final String instationMoment, 
		final String pieceOfText,final String critic , final String link, final String confirm) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "List Bulletins");
		
		super.checkListingExists();
		super.clickOnButton("Create new one");
		super.fillInputBoxIn("heading",heading);
		super.fillInputBoxIn("pieceOfText",pieceOfText);
		super.fillInputBoxIn("critic",critic);
		super.fillInputBoxIn("link",link);
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create new one");
		
		super.checkErrorsExist();
		
	}
	
	@Test
    public void hackingTest() {

        super.navigate("/acme-recipes-22.8/administrator/bulletin/create");
        super.checkPanicExists();

        super.signIn("chef1", "chef1");
        super.navigate("/acme-recipes-22.8/administrator/bulletin/create");
        super.checkPanicExists();
        super.signOut();

        super.signIn("epicure1", "epicure1");
        super.navigate("/acme-recipes-22.8/administrator/bulletin/create");
        super.checkPanicExists();
        super.signOut();
    }


}
