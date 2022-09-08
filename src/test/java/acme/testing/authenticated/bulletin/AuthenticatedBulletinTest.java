package acme.testing.authenticated.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedBulletinTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/bulletin/bulletin.csv", encoding = "utf-8" ,numLinesToSkip = 1)
	@Order(10)
	public void positiveCase(final int recordIndex,final String heading, final String instationMoment, final String pieceOfText,final String critic , final String link) {
		
		//PRUEBA COMO CHEF

		/*super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "List Bulletins");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 1, instationMoment);
		super.checkColumnHasValue(recordIndex, 2, link);
		super.checkColumnHasValue(recordIndex, 3, critic);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title", heading);
		super.checkInputBoxHasValue("creation", instationMoment);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("critic", critic);

		super.signOut();
		*/
		
		//PRUEBA COMO CHEF

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Authenticated", "List Bulletins");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 1, instationMoment);
		super.checkColumnHasValue(recordIndex, 2, pieceOfText);
		super.checkColumnHasValue(recordIndex, 3, critic);
		super.checkColumnHasValue(recordIndex, 4, link);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("instationMoment", instationMoment);
		super.checkInputBoxHasValue("pieceOfText", pieceOfText);
		super.checkInputBoxHasValue("critic", critic);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
				
		//PRUEBA COMO EPICURE
		
		super.signIn("epicure1", "epicure1");

		super.clickOnMenu("Authenticated", "List Bulletins");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 1, instationMoment);
		super.checkColumnHasValue(recordIndex, 2, pieceOfText);
		super.checkColumnHasValue(recordIndex, 3, critic);
		super.checkColumnHasValue(recordIndex, 4, link);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("instationMoment", instationMoment);
		super.checkInputBoxHasValue("pieceOfText", pieceOfText);
		super.checkInputBoxHasValue("critic", critic);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}

}