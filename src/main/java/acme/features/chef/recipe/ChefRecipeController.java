package acme.features.chef.recipe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.recipe.Recipe;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
@RequestMapping("chef/recipe/")
public class ChefRecipeController extends AbstractController<Chef, Recipe> {
	
	@Autowired
	protected ChefRecipeListOwnService listService;
	
	@Autowired
	protected ChefRecipeShowService showService;
	
	@Autowired
	protected ChefRecipeCreateService createService;
	
	@Autowired
	protected ChefRecipeUpdateService updateService;
	
	@Autowired
	protected ChefRecipeDeleteService deleteService;
	
	@Autowired
	protected ChefRecipePublishService publishService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-mine", "list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);	
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish",  "update", this.publishService);

		}
	

	

}
