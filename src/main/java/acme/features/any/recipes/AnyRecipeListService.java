package acme.features.any.recipes;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyRecipeListService implements AbstractListService<Any, Recipe> {
	
	@Autowired
	protected AnyRecipeRepository repository;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public Collection<Recipe> findMany(final Request<Recipe> request){
		assert request !=null;
		
		Collection<Recipe> res;
		res=this.repository.findRecipes();
		return res;
	}
	
	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"code", "heading", "description","preparationNotes","link");
		
	}

}
