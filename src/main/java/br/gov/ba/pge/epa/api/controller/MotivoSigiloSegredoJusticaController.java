package br.gov.ba.pge.epa.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.MotivoSigiloSegredoJustica;
import br.gov.ba.pge.epa.api.repository.MotivoSigiloSegredoJusticaRepository;

@RestController
@RequestMapping("/motivosigilosegredojustica")
public class MotivoSigiloSegredoJusticaController {

	@Autowired
	private MotivoSigiloSegredoJusticaRepository repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<MotivoSigiloSegredoJustica> findById(@PathVariable Long id) {
		Optional<MotivoSigiloSegredoJustica> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping()
	public List<MotivoSigiloSegredoJustica> findAll() {
		return repository.findAll();
	}
}
