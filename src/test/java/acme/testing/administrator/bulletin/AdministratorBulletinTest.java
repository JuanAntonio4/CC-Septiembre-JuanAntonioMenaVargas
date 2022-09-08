package acme.testing.administrator.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorBulletinTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/bulletin/bulletin.csv", encoding = "utf-8" ,numLinesToSkip = 1)
	@Order(10)
	public void positiveCase(final int recordIndex,final String heading, final String instationMoment, final String pieceOfText,final String critic , final String link) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "List Bulletins");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 1, instationMoment);
		super.checkColumnHasValue(recordIndex, 2, pieceOfText);
		super.checkColumnHasValue(recordIndex, 3, critic);
		super.checkColumnHasValue(recordIndex, 4, link);
		

		super.signOut();
	}

}