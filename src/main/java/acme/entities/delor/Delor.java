package acme.entities.delor;

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
public class Delor extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	//El pattern nos lo daran para interpretarlo
	@Column(unique=true)
	@NotBlank
	@Pattern(regexp = "[0-9]{6}:[0-9]{6}$")
	protected String keylet;
	
	
    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @NotNull
    protected Date instationMoment;
    
    @NotBlank
    @Length(min=1, max=100)
    protected String subject;
    
	@NotBlank
	@Length(min=1, max=255)
	protected String explanation;
	
	@NotNull
	protected Money income;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startsAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishesAt;
	
	
	@URL
	protected String moreInfo;
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	protected CookingItem cookingItem;
	
}


	

