package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamCreateService extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/ingredientPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(0)
	public void CreateIngredientTest(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkListingExists();

		super.clickOnButton("Create Ingredient");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create Ingredient");

		super.sortListing(0, "asc");
		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkListingExists();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/pimpamPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void CreatePimpamForIngredientPositiveTest(final int recordIndex, final String code, final String instationMoment, final String title, final String description
		, final String startsAt, final String finishesAt, final String budget, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);

		super.clickOnButton("Pimpams");
		super.clickOnButton("Create Pimpam");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startsAt", startsAt);
		super.fillInputBoxIn("finishesAt", finishesAt);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create Pimpam");

		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkNotErrorsExist();
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/pimpamNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)
	public void CreatePimpamForIngredientNegativeTest(final int recordIndex, final String code, final String instationMoment, final String title, final String description
		, final String startsAt, final String finishesAt, final String budget, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);

		super.clickOnButton("Pimpams");
		super.clickOnButton("Create Pimpam");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startsAt", startsAt);
		super.fillInputBoxIn("finishesAt", finishesAt);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create Pimpam");

		super.checkErrorsExist();
		super.signOut();

	}

}
