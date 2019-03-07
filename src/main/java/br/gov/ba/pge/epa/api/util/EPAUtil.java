package br.gov.ba.pge.epa.api.util;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public static String removerCaracteresNaoNumericos(String valor) {
		String resultado = valor.replaceAll("[^0123456789]", "");
		return resultado;
	}

	public static String getReadTree(String in, String fieldName) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(in);
			JsonNode field = jsonNode.get(fieldName);
			if (field != null && field.isTextual()) {
				return field.asText();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
