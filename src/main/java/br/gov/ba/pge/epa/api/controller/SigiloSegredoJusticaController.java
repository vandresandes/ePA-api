package br.gov.ba.pge.epa.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.enums.EnumSigiloSegredoJustica;
import br.gov.ba.pge.epa.api.model.enums.dto.EnumDto;

@RestController
@RequestMapping("/sigilosegredojustica")
public class SigiloSegredoJusticaController {

	@GetMapping("/{valor}")
	public ResponseEntity<EnumDto> findById(@PathVariable String valor) {
		EnumDto retorno = EnumSigiloSegredoJustica.buscarPorValor(valor);
		return retorno != null ? ResponseEntity.ok(retorno) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<EnumDto> getValores() {
		return EnumSigiloSegredoJustica.getValores();
	}
}
