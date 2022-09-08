package acme.testing.any.peep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TestsAnyPeepList extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/peep.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(0)
	public void testAnyChirpList(final int recordIndex,final String writer, final String pieceOfText, 
		final String instantionMoment, final String email, final String heading ) {

		
		super.clickOnMenu("Anonymous","List Peep");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, writer);
		super.checkColumnHasValue(recordIndex, 1, pieceOfText);
		super.checkColumnHasValue(recordIndex, 2, instantionMoment);
		super.checkColumnHasValue(recordIndex, 3, email);
		super.checkColumnHasValue(recordIndex, 4, heading);

	}
}
