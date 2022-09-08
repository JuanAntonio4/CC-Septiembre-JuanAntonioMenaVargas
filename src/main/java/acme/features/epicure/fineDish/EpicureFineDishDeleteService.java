package acme.features.epicure.fineDish;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Epicure;



@Service
public class EpicureFineDishDeleteService implements AbstractDeleteService<Epicure, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureFineDishRepository repository;

	// AbstractDeleteService<Employer, Duty> interface -------------------------


	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		final int id = request.getPrincipal().getActiveRoleId();
		final Collection<FineDish> finedishs = this.repository.findAllFineDishByEpicureId(id);
		final int finedish_id = request.getModel().getInteger("id");
		final FineDish finedish = this.repository.findFineDishById(finedish_id);
		return finedishs.contains(finedish);
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
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		

		request.bind(entity, errors, "code", "request", "budget", "startsAt", "finishesAt","link","publishedStatus");
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "code", "request", "budget", "startsAt", "finishesAt","link","publishedStatus");
		

	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		final List<Memorandum> memorandums = this.repository.findMemorandumByfinedishId(entity.getId());
		for(final Memorandum p : memorandums) {
			this.repository.delete(p);
		}
		this.repository.delete(entity);
	}

}

