package acme.features.epicure.memorandum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumShowService implements AbstractShowService<Epicure, Memorandum>{
	
	@Autowired
	protected EpicureMemorandumRepository repository;
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Memorandum requested = this.repository.findMemorandumById(id);
		final Collection<Memorandum> patronPatronageReport = this.repository.findMemorandumByEpicureId(request.getPrincipal().getActiveRoleId());
		return patronPatronageReport.contains(requested);
	}
	
	
	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		assert request != null;
		
		Memorandum result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findMemorandumById(id);
		
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model,  "seqNumber", "instantiationMoment", "report", "link", "fineDish.status", "fineDish.code", "fineDish.request", "fineDish.budget", "fineDish.startsAt", "fineDish.finishesAt", "fineDish.link");
	}
	
}