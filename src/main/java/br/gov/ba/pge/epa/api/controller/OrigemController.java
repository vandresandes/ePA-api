package br.gov.ba.pge.epa.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.Origem;
import br.gov.ba.pge.epa.api.repository.OrigemRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/origem")
public class OrigemController {

	@Autowired
	private OrigemRepository repository;

	@GetMapping
	public List<Origem> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

}
