
package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CreateRecipeServiceTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/recipePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateIngredientTest(final int recordIndex, final String code, final String heading, final String description, final String preparationNotes, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Recipes");
		super.checkListingExists();

		super.clickOnButton("Create Recipe!");
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Chef", "List Own Recipes");
		super.checkListingExists();

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/recipeNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeCreateIngredientTest(final int recordIndex, final String code, final String heading, final String description, final String preparationNotes, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Recipes");
		super.checkListingExists();
		super.clickOnButton("Create Recipe!");
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();
		super.signOut();

	}

}
