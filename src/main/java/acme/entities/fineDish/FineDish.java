package acme.entities.fineDish;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.enums.PublishedStatus;
import acme.enums.Status;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import acme.roles.Epicure;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FineDish extends AbstractEntity {

	protected static final long serialVersionUID = 1L;
	
	@NotNull
	protected Status status;
	
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	@Column(unique=true)
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String request;
	 
	@NotNull
	protected Money budget;
	
	@NotNull
	protected PublishedStatus publishedStatus;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startsAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishesAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date creationTime;
	
	@URL
	protected String link;
	
	@ManyToOne
	@Valid
	@NotNull
	@Basic(optional = false)
	protected Chef chef;
	
	@ManyToOne
	@Valid
	@NotNull
	@Basic(optional = false)
	protected Epicure epicure;
	
	
	
	
	
}
