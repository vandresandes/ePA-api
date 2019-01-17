package br.gov.ba.pge.epa.api.util;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class EPAUtil {

	public static boolean isNotBlank(String value) {
		return StringUtils.isNotBlank(value) && !value.equals("null") && !value.equals("undefined");
	}

	public static String adicionarSeparador(List<String> lista, String separador) {
		String resultado = "";
		String separadorAux = "";
		for (String item : lista) {
			resultado += separadorAux + item;
			separadorAux = separador;
		}
		return resultado;
	}
	
	public static Integer getValor(Optional<Integer> optional) {
		return optional.isPresent() ? optional.get() : null;
	}
}
