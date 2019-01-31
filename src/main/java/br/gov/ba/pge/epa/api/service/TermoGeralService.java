package br.gov.ba.pge.epa.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.repository.TermoGeralRepository;

@Service
public class TermoGeralService {

	@Autowired
	private TermoGeralRepository repository;
	
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
