package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cookingItem.CookingItem;
import acme.entities.quantity.Quantity;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefQuantityUpdateService implements AbstractUpdateService<Chef, Quantity>{

	@Autowired
	protected ChefQuantityRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository confRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		final Chef i = this.repository.findChefByUserId(request.getPrincipal().getAccountId());
		final Quantity q = this.repository.findOneQuantityById(request.getModel().getInteger("id"));
		
		return q.getRecipe().getChef().getId()==i.getId();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "number",  "cookingitem.name", "cookingitem.code", "cookingitem.description", "cookingitem.retailPrice", "cookingitem.link", "cookingitem.type");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "number", "cookingitem.name", "cookingitem.code", "cookingitem.description", "cookingitem.retailPrice", "cookingitem.link",  "cookingitem.type");
		
		model.setAttribute("cookingitem", entity.getCookingitem());
		model.setAttribute("recipe", entity.getRecipe());
		model.setAttribute("chefId", request.getPrincipal().getActiveRoleId());
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		
		return this.repository.findOneQuantityById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	
		errors.state(request, !(entity.getRecipe().getStatus().toString().equals("PUBLISHED")), "number", "chef.quantity.recipe.noPublished");

		errors.state(request, entity.getCookingitem().getRetailPrice().getAmount() >= 0.00, "item.retailPrice", "chef.cookingitem.minPrice");
		
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		final CookingItem i = entity.getCookingitem();
		
		if(entity.getCookingitem().getType().equals(acme.entities.cookingItem.CookingItemType.KITCHEN_UTENSIL) && entity.getNumber() > 1) {
			entity.setNumber(1);
		}
		
		this.repository.save(i);
		this.repository.save(entity);
		
		
		
		
	}

}
