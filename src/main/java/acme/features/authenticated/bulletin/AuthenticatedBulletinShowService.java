package acme.features.authenticated.bulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedBulletinShowService implements AbstractShowService<Authenticated, Bulletin> {
	
	@Autowired
	protected AuthenticatedBulletinRepository repository;
	
	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
	
		return true;
	}
	
	@Override
	public Bulletin findOne(final Request<Bulletin> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		final Bulletin res = this.repository.findBulletinById(id);

		return res;
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "heading", "instationMoment", "pieceOfText", "link" , "critic");
		
		
		
	}

}
