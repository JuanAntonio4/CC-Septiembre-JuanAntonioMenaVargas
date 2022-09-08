package acme.testing.any.peep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CreateTestAnyPeep extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/peepPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String writer, final String pieceOfText, 
		final String instantionMoment, final String email, final String heading, final String confirm) {

		super.clickOnMenu("Anonymous","List Peep");
		super.checkListingExists();
		super.clickOnButton("Create new one");
		super.fillInputBoxIn("heading",heading);
		super.fillInputBoxIn("writer",writer);
		super.fillInputBoxIn("pieceOfText",pieceOfText);
		super.fillInputBoxIn("email",email);
		//Creado el atributo confirm en la base de datos para poder aceptar el checkbox requerido
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, writer);
		super.checkColumnHasValue(recordIndex, 1, pieceOfText);
		super.checkColumnHasValue(recordIndex, 3, email);
		super.checkColumnHasValue(recordIndex, 4, heading);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/peepNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String writer, final String pieceOfText, 
		final String instantionMoment, final String email, final String heading, final String confirm) {
		//Misma estructura que en el positivo
		super.clickOnMenu("Anonymous","List Peep");
		super.checkListingExists();
		super.clickOnButton("Create new one");
		super.fillInputBoxIn("heading",heading);
		super.fillInputBoxIn("writer",writer);
		super.fillInputBoxIn("pieceOfText",pieceOfText);
		super.fillInputBoxIn("email",email);
		//Creado el atributo confirm en la base de datos para poder aceptar el checkbox requerido
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
	}
	
		//No existe la posibilidad de hacer un hackingTest
	

}
