package acme.features.epicure.memorandum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumListService implements AbstractListService<Epicure, Memorandum>{

	
	@Autowired
	protected EpicureMemorandumRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public Collection<Memorandum> findMany(final Request<Memorandum> request) {
		assert request != null;
		
		Collection<Memorandum> result;
		final int id = request.getModel().getInteger("id");
		result = this.repository.findMemorandumByFineDishId(id);
		
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model,  "seqNumber", "instantiationMoment", "report", "link", "fineDish.code");
	}
	
}