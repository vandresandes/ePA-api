package br.gov.ba.pge.epa.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.repository.TermoGeralRepository;
import br.gov.ba.pge.epa.api.service.TermoGeralService;

@Service
public class TermoGeralServiceImpl implements TermoGeralService {

	@Autowired
	private TermoGeralRepository repository;

	@Override
	public void deleteById(Long id) {
		if (isValidDelete(id)) {
			repository.deleteById(id);
		}
	}

	private boolean isValidDelete(Long id) {
		Optional<TermoGeral> optional = repository.findById(id);
		if (optional.isPresent()) {
			// TODO só permitir a exclusão se o TermoGeral não estiver vinculado a nenhum Checklist 
			return true;
		}
		return false;
	}

}
