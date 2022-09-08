
package acme.features.any.cookingItem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.cookingItem.CookingItem;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;


@Controller
public class AnyCookingItemController extends AbstractController<Any, CookingItem> {

	@Autowired
	protected AnyCookingItemListIngredientService listIngredientService;

	@Autowired
	protected AnyCookingItemShowService	showService;

	@Autowired
	protected AnyCookingItemListKitchenUtensilService listKitchenUtensilService;
	
	@Autowired
	protected AnyCookingItemListByRecipeService listByRecipeService;


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-ingredient", "list", this.listIngredientService);
		super.addCommand("list-kitchenUtensil", "list", this.listKitchenUtensilService);
		super.addCommand("show", this.showService);
		super.addCommand("list-by-recipe", "list", this.listByRecipeService);

	}

}
