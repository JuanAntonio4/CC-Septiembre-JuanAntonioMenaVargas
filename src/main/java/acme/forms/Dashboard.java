package acme.forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.cookingItem.CookingItemType;
import acme.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	
	private Map<String, Integer> totalsData;
	
	private Map<Pair<String, String>, Map<String, Double>> ingredientRetailPrice;
	
	private Map<CookingItemType, Map<String, Map<String, Double>>> CookingItemsRetailPrice;
	
	private Map<Status, Map<String, Double>> FineDishesBudgets;
	
	//control check
	
	Double ratio;
	// aqui termina control check
	
	private Map<String, Map<String, Double>> pimpamBudget;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	
	public List<String> getTotalsDataKeys() {
		return new ArrayList<String>(Arrays.asList("Ingredient", "Kitchen_Utensil", "Proposed", "Accepted", "Denied"));
	}
	
	public List<String> getDataKeys() {
		return new ArrayList<String>(Arrays.asList("Min", "Max", "Avg", "Dev"));
	}

}
