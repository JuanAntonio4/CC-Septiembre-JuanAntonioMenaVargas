package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/pimpamForIngredient.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(0)
	public void checkChefPimpamIngredientShow(final int recordIndex, final String code, final String instationMoment, final String title, final String description,
		final String startsAt,final String finishesAt, final String budget, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Ingredients");

		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Pimpams");
		super.clickOnListingRecord(recordIndex);
		
	
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("instationMoment", instationMoment);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startsAt", startsAt);
		super.checkInputBoxHasValue("finishesAt", finishesAt);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		
	
	

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/pimpamForKitchenUtensil.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void checkChefPimpamKitchenUtensilShow(final int recordIndex, final String code, final String instationMoment, final String title, final String description,
		final String startsAt,final String finishesAt, final String budget, final String link) {

		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Own Kitchen Utensils");

		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Pimpams");
		super.clickOnListingRecord(recordIndex);
		
	
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("instationMoment", instationMoment);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startsAt", startsAt);
		super.checkInputBoxHasValue("finishesAt", finishesAt);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		
	
	

		super.signOut();
	}

}
