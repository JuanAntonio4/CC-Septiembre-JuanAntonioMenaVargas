package acme.testing.epicure.memorandum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CreateTestEpicureMemorandum extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memorandum/memorandumPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code,final String budget, 
		final String seqNumber, final String instantiationMoment, final String report, final String link2, final String confirm) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "List Fine Dishes");
		super.checkListingExists();
		
        super.clickOnListingRecord(recordIndex);
        
        super.clickOnButton("Create new memorandum asociated");
        
        
		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("link", link2);
		//Creado el atributo confirm en la base de datos para poder aceptar el checkbox requerido
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create new memorandum asociated");
        super.clickOnButton("Memorandums");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 2, report);
		super.checkColumnHasValue(recordIndex, 3, link2);

		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memorandum/memorandumNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void NegativeTest(final int recordIndex, final String status, final String code,final String budget, 
		final String seqNumber, final String instantiationMoment, final String report, final String link2, final String confirm) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "List Fine Dishes");
		super.checkListingExists();
		
        super.clickOnListingRecord(recordIndex);
        
        super.clickOnButton("Create new memorandum asociated");
        
        
		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("link", link2);
		//Creado el atributo confirm en la base de datos para poder aceptar el checkbox requerido
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create new memorandum asociated");
        
		super.checkErrorsExist();

	}
	
	@Test
    @Order(10)
    public void hackingTest() {

        super.navigate("/acme-recipes-22.8/epicure/memorandum/create");
        super.checkPanicExists();
       
        super.signIn("chef1", "chef1");
        super.navigate("/acme-recipes-22.8/epicure/memorandum/create");
        super.checkPanicExists();
        super.signOut();

    }

}
