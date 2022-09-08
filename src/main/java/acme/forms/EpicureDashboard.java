
package acme.forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import acme.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpicureDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	// Attributes -------------------------------------------------------------
	
	
	public List<String> getTotalscurre() {
		return new ArrayList<String>(Arrays.asList("EUR", "USD", "GBP"));
	}
	
	public List<String> getDataKeys() {
		return new ArrayList<String>(Arrays.asList("Min", "Max", "Avg", "Dev"));
	}
	
	
	private Map<Status,  Integer> totalNumberFineDishes;
	
	private Map<Status, Map<String, Map<String, Double>>> FineDishesBudgets;
	

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
