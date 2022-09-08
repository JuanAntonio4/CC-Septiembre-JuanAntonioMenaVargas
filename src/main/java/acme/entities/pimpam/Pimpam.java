package acme.entities.pimpam;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.cookingItem.CookingItem;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pimpam extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	//El pattern nos lo daran para interpretarlo
	@Column(unique=true)
	@NotBlank
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	protected String code;
	
	
    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @NotNull
    protected Date instationMoment;
    
    @NotBlank
    @Length(min=1, max=100)
    protected String title;
    
	@NotBlank
	@Length(min=1, max=255)
	protected String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startsAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishesAt;
	
	@NotNull
	protected Money budget;
	
	@URL
	protected String link;
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	protected CookingItem cookingItem;
	
}


	

