package acme.entities.cookingItem;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CookingItem extends AbstractEntity{
	
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Length(max = 100)
	protected String name;
	
	@Column(unique=true)
	@NotBlank
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	protected String code;
	
	@NotBlank
	@Length(max = 255)
	protected String description;
	
	@NotNull
	@Valid
	protected Money retailPrice;
	
	@URL
	protected String link;
	
	@NotNull
	protected Status status;
	

	@NotNull
	protected CookingItemType type;
	
	@ManyToOne
	@Valid
	@NotNull
	@Basic(optional = false)
	protected Chef chef;
	

	
	
	


}
