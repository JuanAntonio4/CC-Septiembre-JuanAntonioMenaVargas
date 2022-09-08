
package acme.features.chef.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.cookingItem.CookingItem;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefRecipeCreateService implements AbstractCreateService<Chef, Recipe> {

	@Autowired
	protected ChefRecipeRepository repository;

	@Autowired
	protected AdministratorConfigurationRepository	confRepository;


	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		final Chef chef = this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());
		return chef != null;
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "heading", "description", "preparationNotes", "link");

	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link");

		final List<CookingItem> items = this.repository.findManyCookingItem();
		model.setAttribute("items", items);
	}

	@Override
	public Recipe instantiate(final Request<Recipe> request) {
		assert request != null;

		final Recipe res = new Recipe();
		final Chef chef = this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());
		final List<Quantity> quantities = new ArrayList<>();

		res.setChef(chef);
		res.setQuantity(quantities);
		res.setCode(this.generateCode());
		res.setHeading("");
		res.setDescription("");
		res.setPreparationNotes("");
		res.setLink("");
		res.setStatus(acme.entities.recipe.Status.NONE_PUBLISHED);

		return res;
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
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Collection<Configuration> config = this.confRepository.findConfigurations();

		errors.state(request, this.repository.findRecipeByCode(entity.getCode()) == null, "code", "chef.recipe.code.codeNotUnique");

		for (final Configuration c : config) {
			errors.state(request, !c.isSpam(entity.getHeading()), "heading", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getDescription()), "description", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getPreparationNotes()), "preparationNotes", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getLink()), "link", "detected.isSpam");

		}

	}

	@Override
	public void create(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
