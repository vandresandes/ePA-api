package br.gov.ba.pge.epa.api.util;

import org.apache.commons.lang3.StringUtils;

public class EPAUtil {

	public static boolean isNullBlankOrUndefined(String value) {
		if (StringUtils.isNotBlank(value)) {
			return value.equals("null") || value.equals("undefined");
		}
		return true;
	}
}
