package acme.entities.bulletin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bulletin extends AbstractEntity {


	 // Esto es una prueba
	 
	//Serialisation identifier---------------------------------------------
	protected static final long	serialVersionUID	= 1L;

	//Atributes

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date instationMoment;

	@NotBlank
	@Length(min = 1,max = 100)
	protected String heading;

	@NotBlank
	@Length(min = 1,max = 255)
	protected String pieceOfText;
	
	
	protected boolean critic;

	@URL
	protected String link;

}