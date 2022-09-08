package acme.features.chef.cookingItem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.cookingItem.CookingItem;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefCookingItemController extends AbstractController<Chef, CookingItem> {
	
	@Autowired
	protected ChefCookingItemListOwnIngredientService listByIngredientService;
	
	@Autowired
	protected ChefCookingItemShowService showCookingItemService;
	
	@Autowired
	protected ChefCookingItemListOwnKitchenUtensilService listByKitchenUtensilService;
	
	@Autowired
	protected ChefCookingItemCreateIngredientService createCookingItemIngredientService;
	
	@Autowired
	protected ChefCookingItemCreateKitchenUtensilService createCookingItemKitchenUtensilService;
	
	@Autowired
	protected ChefCookingItemDeleteService deleteCookingItemService;
	
	@Autowired
	protected ChefCookingItemUpdateService updateCookingItemService;
	
	@Autowired
	protected ChefCookingItemPublishService publishCookingItemService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("list-ingredient", "list", this.listByIngredientService);
		super.addCommand("list-kitchenUtensil", "list", this.listByKitchenUtensilService);
		super.addCommand("show", this.showCookingItemService);
		super.addCommand("createIngredient","create", this.createCookingItemIngredientService);
		super.addCommand("createKitchenUtensil","create", this.createCookingItemKitchenUtensilService);
		super.addCommand("delete", this.deleteCookingItemService);
		super.addCommand("update", this.updateCookingItemService);
		super.addCommand("publish","update", this.publishCookingItemService);

	}
	
	

}
