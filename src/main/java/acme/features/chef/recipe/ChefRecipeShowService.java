
package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefRecipeShowService implements AbstractShowService<Chef, Recipe> {

	@Autowired
	protected ChefRecipeRepository repository;


	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		final int recipeId = request.getModel().getInteger("id");
		final int chefId = request.getPrincipal().getActiveRoleId();
		
		final Collection<Recipe> recipes = this.repository.findOwnRecipes(chefId);
		final Recipe recipe = this.repository.findRecipeById(recipeId);
		
		return recipes.contains(recipe);
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		final Recipe res = this.repository.findRecipeById(id);

		return res;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link","status");

	}
}
