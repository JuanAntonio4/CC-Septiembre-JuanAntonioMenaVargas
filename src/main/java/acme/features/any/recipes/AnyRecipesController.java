package acme.features.any.recipes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipe.Recipe;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyRecipesController extends AbstractController<Any, Recipe> {
	
	@Autowired
	protected AnyRecipeListService listService;
	
	@Autowired
	protected AnyRecipeShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-published", "list", this.listService);
		super.addCommand("show", this.showService);
	}

}
