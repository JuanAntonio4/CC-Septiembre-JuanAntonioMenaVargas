package acme.features.chef.cookingItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.cookingItem.CookingItem;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefCookingItemUpdateService implements AbstractUpdateService<Chef, CookingItem> {
	
	@Autowired
	protected ChefCookingItemRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository	configurationRepository;
	
	@Override
	public boolean authorise(final Request<CookingItem> request) {
		assert request != null;
		
		final int cookingItemId = request.getModel().getInteger("id");
		final int chefId = request.getPrincipal().getActiveRoleId();
		final CookingItem cookingItem = this.repository.findOneCookingItemById(cookingItemId);	
		return cookingItem.getChef().getId() == chefId && cookingItem.getStatus()==acme.entities.cookingItem.Status.NONE_PUBLISHED;
	}
	
	@Override
	public void bind(final Request<CookingItem> request, final CookingItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "name", "code", "description", "retailPrice", "link");
		
	}
	
	@Override
	public void unbind(final Request<CookingItem> request, final CookingItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"name", "code", "description", "retailPrice", "link");
		
	}
	
	@Override
	public CookingItem findOne(final Request<CookingItem> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final CookingItem res = this.repository.findCookingItemById(id);
		return res;
	}
	
	@Override
	public void validate(final Request<CookingItem> request, final CookingItem entity, final Errors errors) {
		assert request != null;
		
		final Collection<Configuration> config = this.configurationRepository.findConfigurations();
		
		for(final Configuration c : config) {

	
			errors.state(request, !c.isSpam(entity.getName()), "name", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getDescription()), "description", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getLink()), "link", "detected.isSpam");
		}
		
		if(entity.getType() == acme.entities.cookingItem.CookingItemType.INGREDIENT) {
			errors.state(request, entity.getRetailPrice().getAmount() > 0.00, "retailPrice", "chef.precioMinimo");
		} else {
			errors.state(request, entity.getRetailPrice().getAmount() >= 0.00, "retailPrice", "chef.precioMinimo.kitchen");
		}

	}
	
	@Override
	public void update(final Request<CookingItem> request, final CookingItem entity) {
		this.repository.save(entity);
		
	}

}
