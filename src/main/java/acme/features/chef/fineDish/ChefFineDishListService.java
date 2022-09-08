package acme.features.chef.fineDish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.enums.PublishedStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefFineDishListService implements AbstractListService<Chef, FineDish>{
	
	@Autowired
	protected ChefFineDishRepository repository;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public List<FineDish> findMany(final Request<FineDish> request) {
		assert request != null;

		List<FineDish> result;
		final int id;

		id = request.getPrincipal().getActiveRoleId();
		result = this.repository.findFineDishByChefId(id,PublishedStatus.PUBLISHED);

		return result;
	}
	
	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "budget");
	}

}
