package br.gov.ba.pge.epa.api.predicate;

public class BasePredicate {

	private static final String WILD_CARD = "%";

	protected static String containsLowerCase(String searchField) {
		return WILD_CARD + searchField.toLowerCase() + WILD_CARD;
	}

}
