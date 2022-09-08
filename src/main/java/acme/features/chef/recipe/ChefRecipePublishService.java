
package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.entities.recipe.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefRecipePublishService implements AbstractUpdateService<Chef, Recipe> {

	@Autowired
	protected ChefRecipeRepository repository;


	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		final Recipe t = this.repository.findRecipeById(id);
		final Chef i = this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());

		return t.getChef().getId() == i.getId();
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);

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
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		entity.setStatus(Status.PUBLISHED);
		this.repository.save(entity);

	}

}
