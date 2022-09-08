package acme.features.chef.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.quantity.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefQuantiyController extends AbstractController<Chef, Quantity> {
	
	@Autowired
	protected ChefQuantityCreateIngredientService createIngredientService;
	
	@Autowired
	protected ChefQuantityCreateKitchenUtensilService createKitchenUtensilService;
	
	@Autowired
	protected ChefQuantityListService listService;
	
	@Autowired
	protected ChefQuantityShowService showService;
	
	@Autowired
	protected ChefQuantityUpdateService updateService;
	
	@PostConstruct
	protected void initialise() {
		
		super.addCommand("createIngredient","create", this.createIngredientService);
		super.addCommand("createKitchenUtensil","create", this.createKitchenUtensilService);
		super.addCommand("list-by-recipe",  "list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("update", this.updateService);
	}

}
