
package acme.features.chef.cookingItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cookingItem.CookingItem;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefCookingItemShowService implements AbstractShowService<Chef, CookingItem> {

	@Autowired
	protected ChefCookingItemRepository repository;
	@Autowired
	protected AdministratorConfigurationRepository configRepository;


	@Override
	public boolean authorise(final Request<CookingItem> request) {
		assert request != null;

		return true;
	}

	@Override
	public CookingItem findOne(final Request<CookingItem> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");

		return this.repository.findCookingItemById(id);

	}

	@Override
	public void unbind(final Request<CookingItem> request, final CookingItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		final MoneyExchange me = new MoneyExchange(entity.getRetailPrice(), defaultCurrency);
		model.setAttribute("moneyExchange", me.getExchange());

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "status","type");

	}

}
