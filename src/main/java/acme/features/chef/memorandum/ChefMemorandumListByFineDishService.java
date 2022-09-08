package acme.features.chef.memorandum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefMemorandumListByFineDishService implements AbstractListService<Chef, Memorandum>{
	
		@Autowired
		protected ChefMemorandumRepository repository;

		@Override
		public boolean authorise(final Request<Memorandum> request) {
			assert request != null;

			return true;
		}
		
		@Override
		public List<Memorandum> findMany(final Request<Memorandum> request) {
			assert request != null;

			final int id = request.getModel().getInteger("id");
			return this.repository.findByFineDishId(id);
		}
		
		@Override
		public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "seqNumber", "instantiationMoment", "report", "link", "fineDish.code");		
		}
}