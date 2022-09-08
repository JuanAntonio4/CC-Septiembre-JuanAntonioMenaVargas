package acme.testing.chef.cookingItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PublishCookingItemServiceTest extends TestHarness  {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/ingredientForPublish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void	DeleteIngredientTest(final int recordIndex, final String name, final String description, final String retailPrice, final String link) {

		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkListingExists();
		
		super.clickOnButton("Create Ingredient");	
		super.fillInputBoxIn("name",name);
		super.fillInputBoxIn("description",description);
		super.fillInputBoxIn("retailPrice",retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create Ingredient");
		
		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, link);
	
	
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name",name);
		super.checkInputBoxHasValue("description",description);
		super.checkInputBoxHasValue("retailPrice",retailPrice);
		super.checkInputBoxHasValue("link", link);
	
		super.clickOnSubmit("Publish");

		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkNotErrorsExist();
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/kitchenUtensilForPublish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void	DeleteKitchenUtensilTest(final int recordIndex, final String name, final String description, final String retailPrice, final String link) {

		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Own Kitchen Utensils");
		super.checkListingExists();
		
		super.clickOnButton("Create Kitchen Utensil");	
		super.fillInputBoxIn("name",name);
		super.fillInputBoxIn("description",description);
		super.fillInputBoxIn("retailPrice",retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create Kitchen Utensil");
		
		super.clickOnMenu("Chef", "List Own Kitchen Utensils");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, link);
		
	
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name",name);
		super.checkInputBoxHasValue("description",description);
		super.checkInputBoxHasValue("retailPrice",retailPrice);
		super.checkInputBoxHasValue("link", link);
	
		super.clickOnSubmit("Publish");

		super.clickOnMenu("Chef", "List Own Kitchen Utensils");
		super.checkNotErrorsExist();
		super.signOut();

	}

}
