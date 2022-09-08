package acme.testing.chef.cookingItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class IngredientShowServiceTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/ingredients-chef1.csv", numLinesToSkip = 1)
	@Order(0)
	public void checkChef1IngredientShow(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String link) {

		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Own Ingredients");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/ingredients-chef3.csv", numLinesToSkip = 1)
	@Order(1)
	public void checkChef3IngredientShow(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String link) {

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Chef", "List Own Ingredients");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
	
		
	}

}
