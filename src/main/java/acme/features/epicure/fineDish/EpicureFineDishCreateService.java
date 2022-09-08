package acme.features.epicure.fineDish;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
//import acme.entities.configuration.Configuration;
import acme.entities.fineDish.FineDish;
import acme.enums.PublishedStatus;
import acme.enums.Status;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import acme.roles.Epicure;



@Service
public class EpicureFineDishCreateService implements AbstractCreateService<Epicure, FineDish> {

	@Autowired
	protected EpicureFineDishRepository repository;
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setPublishedStatus(PublishedStatus.NONE_PUBLISHED);
		entity.setChef(this.repository.findChefByUsername(request.getModel().getString("chefUN")));
		request.bind(entity, errors, "code", "request", "budget", "startsAt", "finishesAt","link");
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("chefs", this.repository.findChefs());
		request.unbind(entity, model, "code", "request", "budget", "startsAt", "finishesAt","link");
		
	}

	@Override
	public FineDish instantiate(final Request<FineDish> request) {
		assert request != null;

		final FineDish result;
		Date creationTime;
		Date startTime;
		Date finishedTime;
		final Chef chef = new Chef();
		
		final int id = request.getPrincipal().getActiveRoleId();
		creationTime = new Date(System.currentTimeMillis());
		startTime= DateUtils.addMonths( creationTime,1);
		startTime= DateUtils.addMinutes(creationTime, 1);
		finishedTime= DateUtils.addMonths( startTime,1);
		finishedTime= DateUtils.addMinutes(startTime, 1);
		
		
		
		final Money money = new Money();
		money.setAmount(0.0);
		money.setCurrency("EUR");
		
		result = new FineDish();
		result.setStatus(Status.proposed);
		result.setCode(this.generateCode());
		result.setRequest("");
		result.setStartsAt(startTime);
		result.setFinishesAt(finishedTime);
		
		result.setBudget(money);
		result.setLink("");
		result.setPublishedStatus(PublishedStatus.NONE_PUBLISHED);
		result.setChef(chef);
		result.setEpicure(this.repository.findEpicureById(id));
		result.setCreationTime(creationTime);

		return result;
	}
	
	private boolean validateAvailableCurrencyRetailPrice(final Money retailPrice) {

		final String currencies = this.repository.findAvailableCurrencies();
		final List<Object> listCurrencies = Arrays.asList((Object[]) currencies.split(","));

		return listCurrencies.contains(retailPrice.getCurrency());
	}
	
	private String generateCode() {
        String code = "";
        final List<String> alphabet = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

        for (int i = 0; i < 2; i++) {
            code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
        }
        code += ":";
        for (int i = 0; i < 3; i++) {
            code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
        }
        code += "-";
        for (int i = 0; i < 3; i++) {
            code += Integer.toString(ThreadLocalRandom.current().nextInt(0, 9));
        }
        return code;
    }

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		

		final Collection<Configuration> config = this.configurationRepository.findConfigurations();

		for (final Configuration c : config) {
			errors.state(request, !c.isSpam(entity.getRequest()), "request", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getCode()), "code", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getLink()), "link", "detected.isSpam");
		

		}
		
    	final FineDish finedish = this.repository.findFineDishByCode(entity.getCode());
		
		if(finedish != null) {
			errors.state(request, finedish.getId() == entity.getId(), "code", "epicure.item.title.codeNotUnique");
		}
		
		if(!errors.hasErrors("startsAt") && !errors.hasErrors("finishesAt")) {
			
			final Date minimumStartAt= DateUtils.addMonths(entity.getCreationTime(),1);
			errors.state(request,entity.getStartsAt().after(minimumStartAt), "startsAt", "epicure.finedish.error.minimumStartAt");
			
			final Date minimumFinishesAt=DateUtils.addMonths(entity.getStartsAt(), 1);
			errors.state(request,entity.getFinishesAt().after(minimumFinishesAt), "finishesAt", "epicure.finedish.error.minimumFinishesAt");
		
		}
			
		
		if (!errors.hasErrors("budget")) {

			final Money budget = entity.getBudget();
			final boolean availableCurrency = this.validateAvailableCurrencyRetailPrice(budget);
			
			errors.state(request, availableCurrency, "budget", "epicure.budgetNull");

			errors.state(request, entity.getBudget().getAmount() > 0.00, "budget", "authenticated.epicure.finedish.list.label.priceGreatherZero");

			}
		
		
	}

	@Override
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
