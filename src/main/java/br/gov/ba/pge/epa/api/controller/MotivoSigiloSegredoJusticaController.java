package br.gov.ba.pge.epa.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.MotivoSigiloSegredoJustica;
import br.gov.ba.pge.epa.api.repository.MotivoSigiloSegredoJusticaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/motivosigilosegredosusticacontroller")
public class MotivoSigiloSegredoJusticaController {

	@Autowired
	private MotivoSigiloSegredoJusticaRepository repository;

	@GetMapping()
	public List<MotivoSigiloSegredoJustica> findAll() {
		return repository.findAll();
	}
}
