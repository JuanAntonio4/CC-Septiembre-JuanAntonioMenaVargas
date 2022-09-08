package acme.entities.recipe;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.quantity.Quantity;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Recipe extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	

	
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	@Column(unique=true)
	protected String code;
	
	@NotBlank
	@Length(min = 1,max = 100)
	protected String heading;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String description;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String preparationNotes;
	
	@URL
	protected String link;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Chef chef;
	
	@Valid
	@OneToMany(mappedBy="recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	protected List<Quantity> quantity;
	
	@NotNull
	protected Status status;
	
	


}
