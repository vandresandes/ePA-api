package br.gov.ba.pge.epa.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.repository.ChecklistRepository;

@Service
public class ChecklistService {

	@Autowired
	private ChecklistRepository repository;
}
