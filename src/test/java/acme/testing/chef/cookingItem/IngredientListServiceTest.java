package acme.testing.chef.cookingItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class IngredientListServiceTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/ingredients-chef1.csv", numLinesToSkip = 1)
	@Order(0)
	public void checkChef1IngredientListing(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String link) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Own Ingredients");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, link);
		super.signOut();

	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/ingredients-chef3.csv", numLinesToSkip = 1)
	@Order(1)
	public void checkChef3IngredientListing(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String link) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Chef", "List Own Ingredients");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, link);
	
		super.signOut();

	}
	


}
