package br.gov.ba.pge.epa.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.repository.TermoGeralRepository;

@Service
public class TermoGeralService {

	@Autowired
	private TermoGeralRepository repository;
}
