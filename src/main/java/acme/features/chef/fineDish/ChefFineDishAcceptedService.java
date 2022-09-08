package acme.features.chef.fineDish;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;

import acme.enums.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;



@Service
public class ChefFineDishAcceptedService implements AbstractUpdateService<Chef, FineDish> {

	@Autowired
	protected ChefFineDishRepository repository;
	
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		final int id = request.getPrincipal().getActiveRoleId();
		final Collection<FineDish> finedishs = this.repository.findAllFineDishByChefId(id);
		final int finedish_id = request.getModel().getInteger("id");
		final FineDish finedish = this.repository.findFineDishById(finedish_id);
		return finedishs.contains(finedish);
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	
	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		//Intentionally in blank
	}

	

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findFineDishById(id);

		return result;
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		// TODO Auto-generated method stub
		entity.setStatus(Status.accepted);
		this.repository.save(entity);
	}

	
	

}
