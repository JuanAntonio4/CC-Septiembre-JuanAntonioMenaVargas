package acme.entities.quantity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.entities.cookingItem.CookingItem;
import acme.entities.recipe.Recipe;
import acme.enums.Amount;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quantity extends AbstractEntity{
	
	
	private static final long serialVersionUID = 1L;

	@Min(1)
	protected int number;
	
	@Basic(optional = true)
	protected Amount amount;
	
	@ManyToOne
	@Valid
	@NotNull
	@Basic(optional = false)
	protected CookingItem cookingitem;
	
	@ManyToOne
	@Valid
	@NotNull
	@Basic(optional = false)
	protected Recipe recipe;

}
