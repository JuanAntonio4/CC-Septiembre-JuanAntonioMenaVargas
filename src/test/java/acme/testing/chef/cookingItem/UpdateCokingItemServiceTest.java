package acme.testing.chef.cookingItem;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class UpdateCokingItemServiceTest extends TestHarness  {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/ingredientForPublish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(0)
	public void	CreateIngredientTest(final int recordIndex, final String name, final String description, final String retailPrice, final String link) {

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
		
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/ingredientForUpdate.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void	UpdateIngredientTest(final int recordIndex, final String name, final String description, final String retailPrice, final String link) {

		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
	
		super.clickOnButton("Update");
		
		super.fillInputBoxIn("name",name);
		super.fillInputBoxIn("description",description);
		super.fillInputBoxIn("retailPrice",retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");	


		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkNotErrorsExist();
		super.signOut();
		
	
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/kitchenUtensilForPublish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)
	public void	CreateKitechenUtensilTest(final int recordIndex, final String name, final String description, final String retailPrice, final String link) {

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
	
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/cooking-item/kitchenUtensilForUpdate.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(3)
	public void	UpdateKitchenUtensilTest(final int recordIndex, final String name, final String description, final String retailPrice, final String link) {

		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Own Kitchen Utensils");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
	
		super.clickOnButton("Update");
		
		super.fillInputBoxIn("name",name);
		super.fillInputBoxIn("description",description);
		super.fillInputBoxIn("retailPrice",retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");	


		super.clickOnMenu("Chef", "List Own Kitchen Utensils");
		super.checkNotErrorsExist();
		super.signOut();
		
	
		
	}
	
		

}
	



