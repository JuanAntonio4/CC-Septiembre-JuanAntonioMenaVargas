
package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class UpdateRecipeServiceTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/recipeForPublish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(0)
	public void CreateIngredientTest(final int recordIndex, final String heading, final String description, final String preparationNotes, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Recipes");
		super.checkListingExists();

		super.clickOnButton("Create Recipe!");
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.sortListing(1, "asc");
		super.clickOnMenu("Chef", "List Own Ingredients");
		super.checkListingExists();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/recipeForUpdate.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void UpdateIngredientTest(final int recordIndex, final String heading, final String description, final String preparationNotes, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Recipes");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);

		super.clickOnButton("Update");

		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");

		super.clickOnMenu("Chef", "List Own Recipes");
		super.checkNotErrorsExist();
		super.signOut();

	}

}
