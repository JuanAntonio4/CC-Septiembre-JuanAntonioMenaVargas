package acme.features.administrator.bulletin;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorBulletinListService implements AbstractListService<Administrator, Bulletin> {

	@Autowired
	protected AdministratorBulletinRepository repository;


	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Bulletin> findMany(final Request<Bulletin> request) {
		assert request != null;

		Collection<Bulletin> res;
		Calendar calendar;
		Date maxTime;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		maxTime = calendar.getTime();

		res = this.repository.findLastAnnouncement(maxTime);

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