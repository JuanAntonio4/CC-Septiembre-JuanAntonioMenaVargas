package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamListTest extends TestHarness  {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/pimpamForIngredient.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(0)
	public void checkChefPimpamForIngredientListing(final int recordIndex, final String code, final String instationMoment, final String title, final String description,
		final String startsAt,final String finishesAt, final String budget, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Ingredients");

		super.checkListingExists();

		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		super.clickOnButton("Pimpams");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, instationMoment);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.checkColumnHasValue(recordIndex, 3, description);
		super.checkColumnHasValue(recordIndex, 4, startsAt);
		super.checkColumnHasValue(recordIndex, 5, finishesAt);
		super.checkColumnHasValue(recordIndex, 6, budget);
		super.checkColumnHasValue(recordIndex, 7, link);
	

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/pimpamForKitchenUtensil.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void checkChefPimpamForKitchenUtensilListing(final int recordIndex, final String code, final String instationMoment, final String title, final String description,
		final String startsAt,final String finishesAt, final String budget, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Kitchen Utensils");

		super.checkListingExists();

		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		super.clickOnButton("Pimpams");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, instationMoment);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.checkColumnHasValue(recordIndex, 3, description);
		super.checkColumnHasValue(recordIndex, 4, startsAt);
		super.checkColumnHasValue(recordIndex, 5, finishesAt);
		super.checkColumnHasValue(recordIndex, 6, budget);
		super.checkColumnHasValue(recordIndex, 7, link);
	

		super.signOut();
	}

}
