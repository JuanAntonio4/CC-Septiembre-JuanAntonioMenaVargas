package acme.testing.any.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyRecipeTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/recipe/recipe.csv", numLinesToSkip = 1)
	@Order(0)
	public void checkRecipeListing(final int recordIndex, final String code, final String heading, final String description, final String preparationNotes, final String link) {
		
		
		super.clickOnMenu("Anonymous", "List Recipe");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, preparationNotes);
		super.checkColumnHasValue(recordIndex, 4, link);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/recipe/recipe.csv", numLinesToSkip = 1)
	@Order(1)
	public void checkRecipeShow(final int recordIndex, final String code, final String heading, final String description, final String preparationNotes, final String link) {

		super.clickOnMenu("Anonymous", "List Recipe");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("link", link);
		
	}

}
