package br.gov.ba.pge.epa.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.enums.EnumPrioridadeTramitacao;
import br.gov.ba.pge.epa.api.model.enums.dto.EnumDto;

@RestController
@RequestMapping("/prioridadetramitacao")
public class PrioridadeTramitacaoController {

	@GetMapping("/{valor}")
	public ResponseEntity<EnumDto> findById(@PathVariable String valor) {
		EnumDto retorno = EnumPrioridadeTramitacao.buscarPorValor(valor);
		return retorno != null ? ResponseEntity.ok(retorno) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<EnumDto> getValores() {
		return EnumPrioridadeTramitacao.getValores();
	}

}
