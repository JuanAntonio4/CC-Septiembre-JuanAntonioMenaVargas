package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefQuantityShowService implements AbstractShowService<Chef, Quantity>{

	@Autowired
	protected ChefQuantityRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Quantity q = this.repository.findOneQuantityById(request.getModel().getInteger("id"));
		final Chef i = this.repository.findChefByUserId(request.getPrincipal().getAccountId());
		
		return q.getRecipe().getChef().getId() == i.getId();
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Quantity res = this.repository.findOneQuantityById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "number", "amount", "cookingitem.name", "cookingitem.code", "cookingitem.description", "cookingitem.retailPrice", "cookingitem.link", "cookingitem.status", "cookingitem.type");
		
		model.setAttribute("cookingitem", entity.getCookingitem());
		model.setAttribute("recipe", entity.getRecipe());
		model.setAttribute("chefId", request.getPrincipal().getActiveRoleId());
	}

}
