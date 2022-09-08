
package acme.features.chef.cookingItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cookingItem.CookingItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefCookingItemListOwnIngredientService implements AbstractListService<Chef, CookingItem> {

	@Autowired
	protected ChefCookingItemRepository repository;


	@Override
	public Collection<CookingItem> findMany(final Request<CookingItem> request) {
		assert request != null;

		Collection<CookingItem> res;
		final int id = request.getPrincipal().getActiveRoleId();
		res = this.repository.findIngredientsByChefId(id);

		return res;
	}

	@Override
	public boolean authorise(final Request<CookingItem> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CookingItem> request, final CookingItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "type");

	}

}
