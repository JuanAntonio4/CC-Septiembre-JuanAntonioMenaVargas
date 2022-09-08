package acme.features.chef.recipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefRecipeDeleteService implements AbstractDeleteService<Chef, Recipe> {
	
	@Autowired
	protected ChefRecipeRepository repository;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Recipe r = this.repository.findRecipeById(id);
		final Chef c = this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());
		
		return r.getChef().getId()== c.getId();
	}
	
	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "heading", "description", "preparationNotes", "link", "status");
		
	}
	
	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link", "status");
		
	}
	
	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Recipe res = this.repository.findRecipeById(id);
		return res;
	}
	
	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}
	
	@Override
	public void delete(final Request<Recipe> request, final Recipe entity) {
		final List<Quantity> quantities = entity.getQuantity();
		for(final Quantity q: quantities) {
			this.repository.delete(q);
		}
		this.repository.delete(entity);
		
	}

}
