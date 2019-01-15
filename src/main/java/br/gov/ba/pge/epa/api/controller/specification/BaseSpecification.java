package br.gov.ba.pge.epa.api.controller.specification;

public class BaseSpecification<T> {

	private static final String WILD_CARD = "%";

	protected static String containsLowerCase(String searchField) {
		return WILD_CARD + searchField.toLowerCase() + WILD_CARD;
	}
}
