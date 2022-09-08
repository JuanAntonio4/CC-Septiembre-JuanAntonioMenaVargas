package acme.features.chef.quantity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cookingItem.CookingItem;
import acme.entities.cookingItem.CookingItemType;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefQuantityCreateKitchenUtensilService implements AbstractCreateService<Chef, Quantity> {
	
	@Autowired
	protected ChefQuantityRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository confRepository;


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		final Recipe recipe = this.repository.findOneRecipeById(id);
		final Chef chef = this.repository.findChefByUserId(request.getPrincipal().getAccountId());

		return recipe.getChef().getId() == chef.getId();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		entity.setCookingitem(this.repository.findOneCookingItemById(Integer.valueOf(request.getModel().getAttribute("cookingitem").toString())));
		request.bind(entity, errors, "number");

	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final List<CookingItem> items = this.repository.findManyCookingItem(CookingItemType.KITCHEN_UTENSIL);
		model.setAttribute("cookingitems", items);

		request.unbind(entity, model, "number" );

	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		final Quantity res = new Quantity();
		res.setNumber(0);
		final int id = request.getModel().getInteger("id");
		final Recipe recipe = this.repository.findOneRecipeById(id);
		res.setRecipe(recipe);
		return res;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
		
		errors.state(request, !(entity.getRecipe().getStatus().toString().equals("PUBLISHED")), "number", "chef.quantity.recipe.noPublished");

		final Collection<Quantity> items = this.repository.findQuantityByRecipeId(entity.getRecipe().getId());
		final List<CookingItem> aux = new ArrayList<>();
		for (final Quantity q : items) {
			aux.add(q.getCookingitem());
		}


		

	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
