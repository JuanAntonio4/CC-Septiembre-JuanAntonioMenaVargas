
package acme.features.chef.cookingItem;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.cookingItem.CookingItem;
import acme.entities.cookingItem.CookingItemType;
import acme.entities.cookingItem.Status;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefCookingItemCreateKitchenUtensilService implements AbstractCreateService<Chef, CookingItem> {

	@Autowired
	protected ChefCookingItemRepository				repository;

	@Autowired
	protected AdministratorConfigurationRepository	configurationRepository;


	@Override
	public boolean authorise(final Request<CookingItem> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CookingItem> request, final CookingItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "description", "retailPrice", "link");

	}

	@Override
	public void unbind(final Request<CookingItem> request, final CookingItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link");

	}

	@Override
	public CookingItem instantiate(final Request<CookingItem> request) {
		assert request != null;

		final CookingItem result = new CookingItem();
		final Chef chef = this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());

		final Money money = new Money();
		money.setCurrency("EUR");
		money.setAmount(0.00);

		result.setName("");
		result.setCode(this.generateCode());
		result.setDescription("");
		result.setRetailPrice(money);
		result.setLink("");
		result.setStatus(Status.NONE_PUBLISHED);
		result.setType(CookingItemType.KITCHEN_UTENSIL);
		result.setChef(chef);

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
	public void validate(final Request<CookingItem> request, final CookingItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Collection<Configuration> config = this.configurationRepository.findConfigurations();

		errors.state(request, this.repository.findCookingItemByCode(entity.getCode()) == null, "code", "chef.kitchenutensil.title.codeNotUnique");

		for (final Configuration c : config) {

			errors.state(request, !c.isSpam(entity.getName()), "name", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getDescription()), "description", "detected.isSpam");
			errors.state(request, !c.isSpam(entity.getLink()), "link", "detected.isSpam");
		}

		if (!errors.hasErrors("retailPrice")) {

			final Money retailPrice = entity.getRetailPrice();
			final boolean availableCurrency = this.validateAvailableCurrencyRetailPrice(retailPrice);
			errors.state(request, availableCurrency, "retailPrice", "chef.retailpriceNull");

			if (entity.getType().equals(acme.entities.cookingItem.CookingItemType.KITCHEN_UTENSIL)) {
				final boolean retailPriceKitchenUtensil = retailPrice.getAmount() > 0.;
				errors.state(request, retailPriceKitchenUtensil, "retailPrice", "chef.precioMinimo");

			} else if (entity.getType().equals(acme.entities.cookingItem.CookingItemType.INGREDIENT)) {
				final boolean retailPriceIngredient = retailPrice.getAmount() >= 0.;
				errors.state(request, retailPriceIngredient, "retailPrice", "chef.precioMinimo2");

			}

		}

	}

	@Override
	public void create(final Request<CookingItem> request, final CookingItem entity) {
		assert request != null;
		assert entity != null;

		entity.setChef(this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId()));

		entity.setStatus(Status.NONE_PUBLISHED);
		this.repository.save(entity);
	}

}
