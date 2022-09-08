
package acme.entities.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.util.Pair;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	protected String spamWords;

	@NotNull
	@Range(min = 0, max = 1)
	protected Double spamThreshold;

	@NotBlank
	@Pattern(regexp = "[A-Z]{3}")
	protected String defaultCurr;

	@NotBlank
	@Pattern(regexp = "([A-Z]{3})(,\\s*[A-Z]{3})*")
	protected String acceptedCurr;


	public boolean isSpam(final String text) {
		final String[] lowerCaseText = text.toLowerCase().replaceAll("\\s+", " ").split(" ");
		double spamCount = 0;
		final List<Pair<String, Double>> ls = new ArrayList<Pair<String, Double>>();

		final String[] sp = this.spamWords.split(",");

		for (final String s : sp) {
			final String[] ss = s.split(";");
			ls.add(Pair.of(ss[0].replace("(", ""), Double.parseDouble(ss[1].replace(")", ""))));
		}

		final List<String> listaIntroduzco = new ArrayList<String>();
		for (final String i : lowerCaseText) {
			listaIntroduzco.add(i);
		}
		for (final Pair<String, Double> s : ls) {
				final String[] aa = s.getFirst().split(" ");

				switch (aa.length) {
				case 1:
					for (int i = 0; i < listaIntroduzco.size(); i++) {
						if (listaIntroduzco.get(i).equals(aa[0])) {
							spamCount = spamCount + s.getSecond();
						}
					}

					break;
				case 2:
					for (int i = 0; i < listaIntroduzco.size(); i++) {
						if (listaIntroduzco.get(i).equals(aa[0]) && listaIntroduzco.get(i + 1).equals(aa[1])) {
							spamCount = spamCount + s.getSecond();
						}
					}

					break;
				case 3:

					for (int i = 0; i < listaIntroduzco.size(); i++) {
						if (listaIntroduzco.get(i).equals(aa[0]) && listaIntroduzco.get(i + 1).equals(aa[1]) && listaIntroduzco.get(i + 2).equals(aa[1])) {
							spamCount = spamCount + s.getSecond();
						}
					}

					break;
				case 4:
					for (int i = 0; i < listaIntroduzco.size(); i++) {
						if (listaIntroduzco.get(i).equals(aa[0]) && listaIntroduzco.get(i + 1).equals(aa[1]) && listaIntroduzco.get(i + 2).equals(aa[1]) && listaIntroduzco.get(i + 3).equals(aa[1])) {
							spamCount = spamCount + s.getSecond();
						}
					}

					break;

				}

			}
		
		final Double umbral = spamCount / lowerCaseText.length;

		return umbral >= this.spamThreshold;

		}
		
	}

