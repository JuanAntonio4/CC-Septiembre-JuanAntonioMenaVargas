package acme.features.authenticated.bulletin;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedBulletinListService implements AbstractListService<Authenticated, Bulletin> {

	@Autowired
	protected AuthenticatedBulletinRepository repository;


	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public List<Bulletin> findMany(final Request<Bulletin> request) {
		assert request != null;
		
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return this.repository.findBulletins(calendar.getTime());
		
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "instationMoment", "pieceOfText", "link" , "critic");
	}
}
