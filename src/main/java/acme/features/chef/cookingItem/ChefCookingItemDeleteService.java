
package acme.features.chef.cookingItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cookingItem.CookingItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefCookingItemDeleteService implements AbstractDeleteService<Chef, CookingItem> {

	@Autowired
	protected ChefCookingItemRepository repository;


	@Override
	public boolean authorise(final Request<CookingItem> request) {
		assert request != null;

		final int cookingItemId = request.getModel().getInteger("id");
		final int chefId = request.getPrincipal().getActiveRoleId();
		final CookingItem cookingItem = this.repository.findOneCookingItemById(cookingItemId);
		return cookingItem.getChef().getId() == chefId;
	}

	@Override
	public void bind(final Request<CookingItem> request, final CookingItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "description", "retailPrice", "link", "type");

	}

	@Override
	public void unbind(final Request<CookingItem> request, final CookingItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "type");
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
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<CookingItem> request, final CookingItem entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
