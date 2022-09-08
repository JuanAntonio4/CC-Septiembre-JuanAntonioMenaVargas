package acme.features.epicure.fineDish;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.fineDish.FineDish;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;


@Service
public class EpicureFineDishUpdateService implements AbstractUpdateService<Epicure, FineDish> {

	
	
	@Autowired
	protected EpicureFineDishRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;
	
	
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
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		entity.setChef(this.repository.findChefByUsername(request.getModel().getString("chefUN")));
		request.bind(entity, errors, "code", "request", "budget", "startsAt", "finishesAt","link");
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("chefs", this.repository.findChefs());
		
		
		request.unbind(entity, model, "status","code", "request", "budget", "startsAt", "finishesAt","link");
		
	}

	
	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final FineDish finedish = this.repository.findFineDishByCode(entity.getCode());
		
		if(finedish != null) {
			errors.state(request, finedish.getId() == entity.getId(), "code", "chef.item.title.codeNotUnique");
		}
		
		errors.state(request, entity.getBudget().getAmount() > 0.00, "budget", "authenticated.epicure.finedish.list.label.priceGreatherZero");
		

		final Date minimumStartAt= DateUtils.addMonths(entity.getCreationTime(),1);
		errors.state(request,entity.getStartsAt().after(minimumStartAt), "startsAt", "epicure.finedish.error.minimumStartAt");
		
		final Date minimumFinishesAt=DateUtils.addMonths(entity.getStartsAt(), 1);
		errors.state(request,entity.getFinishesAt().after(minimumFinishesAt), "finishesAt", "epicure.finedish.error.minimumFinishesAt");
		
		final Collection<Configuration> config = this.configurationRepository.findConfigurations();

		for (final Configuration c : config) {
			errors.state(request, !c.isSpam(entity.getRequest()), "request", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getCode()), "code", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getLink()), "link", "detected.isSpam");

		}
	
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
	public void update(final Request<FineDish> request, final FineDish entity) {
		// TODO Auto-generated method stub
		this.repository.save(entity);
	}

}
