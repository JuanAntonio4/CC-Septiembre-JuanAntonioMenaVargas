package acme.features.any.cookingItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cookingItem.CookingItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyCookingItemShowService implements AbstractShowService<Any, CookingItem> {
	
	@Autowired
	protected AnyCookingItemRepository repository;
	
	@Override
	public CookingItem findOne(final Request<CookingItem> request) {
		final Integer id = request.getModel().getInteger("id");
		return this.repository.findCookingItemById(id);
	}
	
	@Override
	public void unbind(final Request<CookingItem> request, final CookingItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,  "name", "code", "description", "retailPrice" , "link", "type");
		
	
	}
	
	@Override
	public boolean authorise(final Request<CookingItem> request) {
		assert request != null;
		
		return true;
	}
}
