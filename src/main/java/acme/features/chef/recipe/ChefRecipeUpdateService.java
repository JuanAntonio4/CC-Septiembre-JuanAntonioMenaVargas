package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.recipe.Recipe;
import acme.entities.recipe.Status;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefRecipeUpdateService implements AbstractUpdateService<Chef, Recipe>{
	
	@Autowired
	protected ChefRecipeRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository confRepository;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Chef i = this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());
		final Recipe t = this.repository.findRecipeById(id);
		
		return t.getChef().getId()==i.getId() && t.getStatus()==Status.NONE_PUBLISHED;
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "heading", "description", "preparationNotes", "link");
		
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link");
		
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Recipe res =  this.repository.findRecipeById(id);
		return res;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Collection<Configuration> config = this.confRepository.findConfigurations();
		
		for(final Configuration c : config) {
			errors.state(request, !c.isSpam(entity.getHeading()), "heading", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getDescription()), "description", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getPreparationNotes()), "preparationNotes", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getLink()), "link", "detected.isSpam");
			
		
		}
		
		final Recipe recipe = this.repository.findRecipeByCode(entity.getCode());
		
		if(recipe != null) {
			errors.state(request, recipe.getId() == entity.getId(), "code", "inventor.item.title.codeNotUnique");
		}
		
	}

	@Override
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
