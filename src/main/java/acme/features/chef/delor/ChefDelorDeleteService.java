package acme.features.chef.delor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefDelorDeleteService implements AbstractDeleteService<Chef, Delor> {

	
	@Autowired
	protected ChefDelorRepository repository;
	
	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;
		
		return true;
	}
		
		@Override
		public void bind(final Request<Delor> request, final Delor entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			request.bind(entity, errors, "keylet", "instationMoment","subject", "explanation","income","startsAt","finishesAt","moreInfo");
			
		}
		
		@Override
		public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model,  "keylet", "instationMoment","subject", "explanation","income","startsAt","finishesAt","moreInfo");
			
		}
		
		@Override
		public Delor findOne(final Request<Delor> request) {
			assert request != null;
			
			final int id = request.getModel().getInteger("id");
			final Delor res = this.repository.findOnePimpamById(id);
			return res;
		}
		
		@Override
		public void validate(final Request<Delor> request, final Delor entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
		}
		
		@Override
		public void delete(final Request<Delor> request, final Delor entity) {
			assert request != null;
			assert entity != null;

			this.repository.delete(entity);
			
		}
	
}
