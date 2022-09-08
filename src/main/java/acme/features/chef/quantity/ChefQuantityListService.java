package acme.features.chef.quantity;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cookingItem.CookingItem;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;
import acme.entities.recipe.Status;

@Service
public class ChefQuantityListService implements AbstractListService<Chef, Quantity>{

	@Autowired
	protected ChefQuantityRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Recipe t = this.repository.findOneRecipeById(request.getModel().getInteger("id"));
		final Chef i = this.repository.findChefByUserId(request.getPrincipal().getAccountId());
		
		
		return t.getChef().getId()==i.getId();
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		return this.repository.findQuantityByRecipeId(id);
		
		
	}
	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;
		
		int masterId;
		Recipe recipe;

		
		masterId = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(masterId);
		
		Status me = recipe.getStatus();
		
		model.setAttribute("statusreci", me.toString());
		int chefid = recipe.getChef().getId();
		
		
		List<CookingItem> lutensil = this.repository.findKitchenUtensilsByChefId(chefid);
		List<CookingItem> lingre = this.repository.findIngredientsByChefId(chefid);
		model.setAttribute("lingre", lingre.size());
		model.setAttribute("lutensil", lutensil.size());
		
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Recipe t = this.repository.findOneRecipeById(request.getModel().getInteger("id"));
		model.setAttribute("tStatus", t.getStatus());
		
		
		
		
		
		request.unbind(entity, model, "number", "cookingitem.name", "cookingitem.code", "cookingitem.retailPrice", "cookingitem.status","cookingitem.type");
		
		
		
	}

}
