package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cookingItem.Status;
import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefPimpamShowService implements AbstractShowService<Chef, Pimpam> {
	@Autowired
	protected ChefPimpamRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Pimpam res = this.repository.findOnePimpamById(id);
		
		return res;
	}
	
//	@Override
//	public void unbind(final Request<Pimpam> request, final Collection<Pimpam> entities, final Model model) {
//		assert request != null;
//		assert !CollectionHelper.someNull(entities);
//		assert model != null;
//		
//		int masterId;
//		CookingItem ci;
//
//		
//		masterId = request.getModel().getInteger("id");
//		ci = this.repository.findOneCookingItemById(masterId);
//		
//		final Status me = ci.getStatus();
//		
//		model.setAttribute("statusci", me.toString());
//
//	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final Status me = entity.getCookingItem().getStatus();
		model.setAttribute("statusci", me.toString());
		
		request.unbind(entity, model, "code", "instationMoment","title", "description","startsAt","finishesAt", "budget","link");
		

	}

}
