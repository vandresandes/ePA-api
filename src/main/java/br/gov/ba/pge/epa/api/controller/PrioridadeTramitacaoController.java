package br.gov.ba.pge.epa.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.PrioridadeTramitacao;
import br.gov.ba.pge.epa.api.repository.PrioridadeTramitacaoRepository;

@RestController
@RequestMapping("/prioridadetramitacaocontroller")
public class PrioridadeTramitacaoController {

	@Autowired
	private PrioridadeTramitacaoRepository repository;
	
	@GetMapping()
	public List<PrioridadeTramitacao> findAll() {
		return repository.findAll();
	}
}
