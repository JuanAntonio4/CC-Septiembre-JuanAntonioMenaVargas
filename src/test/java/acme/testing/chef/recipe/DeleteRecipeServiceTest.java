
package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class DeleteRecipeServiceTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/recipeForDelete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void DeleteRecipeTest(final int recordIndex, final String heading, final String description, final String preparationNotes, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Recipes");
		super.checkListingExists();

		super.sortListing(1, "asc");

		super.clickOnButton("Create Recipe!");
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Chef", "List Own Recipes");
		super.checkListingExists();

		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, preparationNotes);
		super.checkColumnHasValue(recordIndex, 4, link);

		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("link", link);

		super.clickOnSubmit("Delete");

		super.clickOnMenu("Chef", "List Own Recipes");
		super.checkNotErrorsExist();
		super.signOut();

	}

}
