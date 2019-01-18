package br.gov.ba.pge.epa.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.SigiloSegredoJustica;
import br.gov.ba.pge.epa.api.repository.SigiloSegredoJusticaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sigilosegredojusticacontroller")
public class SigiloSegredoJusticaController {

	@Autowired
	private SigiloSegredoJusticaRepository repository;

	@GetMapping()
	public List<SigiloSegredoJustica> findAll() {
		return repository.findAll();
	}
}
