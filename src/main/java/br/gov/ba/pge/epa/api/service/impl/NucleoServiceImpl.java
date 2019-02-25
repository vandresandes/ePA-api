package br.gov.ba.pge.epa.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.repository.NucleoRepository;
import br.gov.ba.pge.epa.api.service.NucleoService;

@Service
public class NucleoServiceImpl implements NucleoService {

	@Autowired
	private NucleoRepository repository;

	@Override
	public void deleteById(Long id) {
		repository.findById(id);
	}
}
