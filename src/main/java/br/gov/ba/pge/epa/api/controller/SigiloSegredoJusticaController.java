package br.gov.ba.pge.epa.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.SigiloSegredoJustica;
import br.gov.ba.pge.epa.api.repository.SigiloSegredoJusticaRepository;

@RestController
@RequestMapping("/sigilosegredojusticacontroller")
public class SigiloSegredoJusticaController {

	@Autowired
	private SigiloSegredoJusticaRepository repository;

	@GetMapping("/{id}")
	public ResponseEntity<SigiloSegredoJustica> findById(@PathVariable Long id) {
		Optional<SigiloSegredoJustica> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping()
	public List<SigiloSegredoJustica> findAll() {
		return repository.findAll();
	}
}
