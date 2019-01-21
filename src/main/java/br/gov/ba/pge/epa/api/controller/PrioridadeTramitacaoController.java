package br.gov.ba.pge.epa.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.PrioridadeTramitacao;
import br.gov.ba.pge.epa.api.repository.PrioridadeTramitacaoRepository;

@RestController
@RequestMapping("/prioridadetramitacaocontroller")
public class PrioridadeTramitacaoController {

	@Autowired
	private PrioridadeTramitacaoRepository repository;

	@GetMapping("/{id}")
	public ResponseEntity<PrioridadeTramitacao> findById(@PathVariable Long id) {
		Optional<PrioridadeTramitacao> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping()
	public List<PrioridadeTramitacao> findAll() {
		return repository.findAll();
	}
}
