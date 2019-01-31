package br.gov.ba.pge.epa.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.repository.NucleoRepository;

@Service
public class NucleoService {

	@Autowired
	private NucleoRepository repository;

	public void deleteById(Long id) {
		repository.findById(id);
	}
}
