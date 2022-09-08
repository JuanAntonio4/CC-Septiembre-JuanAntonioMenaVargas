package acme.features.any.cookingItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cookingItem.CookingItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyCookingItemListIngredientService implements AbstractListService<Any, CookingItem>{
	
	@Autowired
	protected AnyCookingItemRepository repository;

	@Override
	public boolean authorise(final Request<CookingItem> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public Collection<CookingItem> findMany(final Request<CookingItem> request){
		assert request !=null;
		
		Collection<CookingItem> res;
		res=this.repository.findPublishedIngredients();
		return res;
	}
	
	@Override
	public void unbind(final Request<CookingItem> request, final CookingItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "code", "description", "retailPrice" , "link", "type");
		
	}
}
