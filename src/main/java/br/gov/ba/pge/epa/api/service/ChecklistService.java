package br.gov.ba.pge.epa.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.model.Checklist;
import br.gov.ba.pge.epa.api.repository.ChecklistRepository;

@Service
public class ChecklistService {

	@Autowired
	private ChecklistRepository repository;
	
	public void deleteById(Long id) {
		if (isValidDelete(id)) {
			repository.deleteById(id);
		}
	}

	private boolean isValidDelete(Long id) {
		Optional<Checklist> optional = repository.findById(id);
		if (optional.isPresent()) {
			// TODO verificar regras 
			return true;
		}
		return false;
	}

}
