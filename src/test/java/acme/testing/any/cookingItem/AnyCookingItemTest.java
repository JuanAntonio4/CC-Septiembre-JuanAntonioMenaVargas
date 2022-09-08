package acme.testing.any.cookingItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyCookingItemTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/cooking-item/ingredients.csv", numLinesToSkip = 1)
	@Order(0)
	public void checkIngredientListing(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String link,final String type) {
		
		
		super.clickOnMenu("Anonymous", "List Ingredients");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, link);
		super.checkColumnHasValue(recordIndex, 5, type);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/cooking-item/ingredients.csv", numLinesToSkip = 1)
	@Order(1)
	public void checkIngredientShow(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String link, final String type) {

		super.clickOnMenu("Anonymous", "List Ingredients");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("type", type);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/cooking-item/kitchen-utensils.csv", numLinesToSkip = 1)
	@Order(2)
	public void checkKitchenUtensilListing(final int recordIndex, final String name, final String code , final String description, final String retailPrice, final String link, final String type) {
	
		super.clickOnMenu("Anonymous", "List Kitchen Utensil");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, link);
		super.checkColumnHasValue(recordIndex, 5, type);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/cooking-item/kitchen-utensils.csv", numLinesToSkip = 1)
	@Order(3)
	public void checkKitchenUtensilShow(final int recordIndex, final String name, final String code, final String description,  final String retailPrice, final String link, final String type) {

		super.clickOnMenu("Anonymous", "List Kitchen Utensil");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("type", type);
		
	}

}
