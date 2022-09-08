package acme.features.administrator.bulletin;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.entities.configuration.Configuration;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBulletinCreateService implements AbstractCreateService<Administrator, Bulletin> {

	@Autowired
	protected AdministratorBulletinRepository	repository;
	@Autowired
	protected AdministratorConfigurationRepository	configurationRepository;


	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public Bulletin instantiate(final Request<Bulletin> request) {
		assert request != null;
		Bulletin res;
		res = new Bulletin();
		return res;
	}

	@Override
	public void bind(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date ActualMoment;

		ActualMoment = new Date(System.currentTimeMillis() - 1);
		entity.setInstationMoment(ActualMoment);
		request.bind(entity, errors, "heading", "pieceOfText", "link" , "critic");
		entity.setInstationMoment(ActualMoment);

	}

	@Override
	public void validate(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
		final Collection<Configuration> config = this.configurationRepository.findConfigurations();
		for(final Configuration c : config) {
			
			errors.state(request, !c.isSpam(entity.getHeading()), "heading", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getPieceOfText()), "pieceOfText", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getLink()), "link", "detected.isSpam");
		}
	
		

		final boolean confirm = request.getModel().getBoolean("confirm");
		errors.state(request, confirm, "confirm", "administrator.announcement.accept.error");

	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "pieceOfText", "link" , "critic");
		model.setAttribute("confirm", false);
	}

	@Override
	public void create(final Request<Bulletin> request, final Bulletin entity) {
		assert request != null;
		assert entity != null;
		
		Date ActualMoment;

		ActualMoment = new Date(System.currentTimeMillis() - 1);
		entity.setInstationMoment(ActualMoment);

		this.repository.save(entity);
	}
}