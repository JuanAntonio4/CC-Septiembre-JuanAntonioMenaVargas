package acme.features.chef.memorandum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.fineDish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefMemorandumCreateService implements AbstractCreateService<Chef, Memorandum> {

	@Autowired
	protected ChefMemorandumRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository	configurationRepository;

	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		boolean result;
		int fineDishId;
		FineDish fineDish;

		fineDishId = request.getModel().getInteger("id");
		fineDish = this.repository.findFineDishById(fineDishId);
		result = request.getPrincipal().getActiveRoleId() == fineDish.getChef().getId();

		return result;
	}

	@Override
	public Memorandum instantiate(final Request<Memorandum> request) {
		assert request != null;

		Memorandum result;
		int fineDishId;
		FineDish fineDish;
		Date date;
		final String numSeqMemorandum;		
		int MemorandumId;
		
		fineDishId = request.getModel().getInteger("id");
		fineDish = this.repository.findFineDishById(fineDishId);
		date = new Date();
		MemorandumId = request.getModel().getInteger("id");
		
		result = new Memorandum();
		result.setFineDish(fineDish);
		result.setInstantiationMoment(date);
		result.setSeqNumber(this.generateCode(fineDish.getCode()));

		return result;
	}
	
	private String generateCode(final String fineDishCode) {
		final List<String> code = new ArrayList<>();
		
		code.add(fineDishCode);
		code.add(String.format("%04d" , this.repository.findMemorandum().size()+1));
		
		return String.join(":", code);
	}
	
	@Override
	public void bind(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "seqNumber", "instantiationMoment", "report", "link");
		
	}
	
	@Override
	public void validate(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Boolean isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "chef.memorandum.form.accept.error");
		
		final Collection<Configuration> config = this.configurationRepository.findConfigurations();	
		for(final Configuration c : config) {
	
			errors.state(request, !c.isSpam(entity.getReport()), "report", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getLink()), "link", "detected.isSpam");
		}
	}
	
	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"seqNumber", "instantiationMoment", "report", "link");
		model.setAttribute("id", request.getModel().getAttribute("id"));
		model.setAttribute("confirm", "false");
	}	

	@Override
	public void create(final Request<Memorandum> request, final Memorandum entity) {
		assert request != null;
		assert entity != null;
		
		entity.setInstantiationMoment(new Date());
		entity.setSeqNumber(this.generateCode(entity.getFineDish().getCode()));

		this.repository.save(entity);
		
	}

}