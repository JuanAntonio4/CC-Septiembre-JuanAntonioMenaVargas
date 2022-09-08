/*
 * AdministratorUserAccountUpdateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.configurations;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator, Configuration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationRepository repository;

	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "spamWords","spamThreshold", "defaultCurr", "acceptedCurr");
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "spamWords","spamThreshold", "defaultCurr", "acceptedCurr");
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;

		final Optional<Configuration> config = this.repository.findConfigurations().stream().findFirst();
		return config.isPresent() ? config.get() : null;
	}

	@Override
	public void validate(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		errors.state(request, entity.getAcceptedCurr().contains(entity.getDefaultCurr()), "defaultCurr", "authenticated.administrator.fallo");
		
		final Collection<Configuration> config = this.repository.findConfigurations();	
		for(final Configuration c : config) {
		
			errors.state(request, !c.isSpam(entity.getDefaultCurr()), "defaultCurr", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getAcceptedCurr()), "acceptedCurr", "detected.isSpam");
		}
	}

	@Override
	public void update(final Request<Configuration> request, final Configuration entity) {
		

		this.repository.save(entity);
		
	}

}
