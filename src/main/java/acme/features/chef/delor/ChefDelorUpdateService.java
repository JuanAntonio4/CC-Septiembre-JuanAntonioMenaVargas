package acme.features.chef.delor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.delor.Delor;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;


@Service
public class ChefDelorUpdateService implements AbstractUpdateService<Chef, Delor> {

	@Autowired
	protected ChefDelorRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository confRepository;
	
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
		
		request.bind(entity, errors,"keylet", "instationMoment","subject", "explanation","income","startsAt","finishesAt","moreInfo");
	
	}
	
	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"keylet", "instationMoment","subject", "explanation","income","startsAt","finishesAt","moreInfo");
	
	}
	
@Override
	public Delor findOne(final Request<Delor> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Delor res =  this.repository.findOnePimpamById(id);
		return res;
	}
	@Override
	public void validate(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Collection<Configuration> config = this.confRepository.findConfigurations();
		
		for(final Configuration c : config) {

			errors.state(request, !c.isSpam(entity.getExplanation()), "description", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getSubject()), "title", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getMoreInfo()), "link", "detected.isSpam");
			
		
		}

//		if(entity.getInstationMoment()!=null) {
//        final Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(entity.getInstationMoment());
//        final String day= String.format("%02d" , calendar2.get(Calendar.DAY_OF_MONTH));
//        final String month= String.format("%02d" , calendar2.get(Calendar.MONTH));
//        final String year = String.valueOf(calendar2.get(Calendar.YEAR)).substring(2);
//        final String[] codesplit = entity.getCode().split("-");
//        boolean bol1;
//        boolean bol2;					
//        boolean bol3;
//        
//        bol1 = codesplit[0] == year.substring(2);
//        bol2 = codesplit[1] == year;
//        bol3 = codesplit[2] == year;
//
//        errors.state(request,(bol1&bol2&bol3) , "code", "administrator.configuration.currency.notExist");
//		}
      
		final Delor ci = this.repository.findPimpamByCode(entity.getKeylet());
		
		if(ci != null) {
			errors.state(request, ci.getId() == entity.getId(), "code", "inventor.item.title.codeNotUnique");
		}
		
		
		
		
	}
	
	@Override
public void update(final Request<Delor> request, final Delor entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
}
