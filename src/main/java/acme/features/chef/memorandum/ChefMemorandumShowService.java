package acme.features.chef.memorandum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefMemorandumShowService implements AbstractShowService<Chef, Memorandum>{
	
		@Autowired
		protected ChefMemorandumRepository repository;
			
		
		@Override
		public boolean authorise(final Request<Memorandum> request) {
			assert request != null;

			boolean res;
			int memorandumId;
			Memorandum memorandum;

			memorandumId = request.getModel().getInteger("id");
			memorandum = this.repository.findOneMemorandumById(memorandumId);
			res = memorandum != null && memorandum.getFineDish().getChef().getId() == request.getPrincipal().getActiveRoleId();

			return res;
		}
		
		@Override
		public Memorandum findOne(final Request<Memorandum> request) {
			assert request != null;

			Memorandum res;
			int memorandumId;

			memorandumId = request.getModel().getInteger("id");
			res = this.repository.findOneMemorandumById(memorandumId);

			return res;
		}
		
		@Override
		public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "seqNumber", "instantiationMoment", "report", "link", "fineDish.status", "fineDish.code", "fineDish.request", "fineDish.budget", "fineDish.startsAt", "fineDish.finishesAt", "fineDish.link");
			
		}

		

}