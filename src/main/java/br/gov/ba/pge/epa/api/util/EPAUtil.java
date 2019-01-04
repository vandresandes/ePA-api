package br.gov.ba.pge.epa.api.util;

import org.apache.commons.lang3.StringUtils;

public class EPAUtil {

	public static boolean isNotBlank(String value) {
		return StringUtils.isNotBlank(value) && !value.equals("null") && !value.equals("undefined");
	}
}
