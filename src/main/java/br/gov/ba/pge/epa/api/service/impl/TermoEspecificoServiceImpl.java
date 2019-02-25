package br.gov.ba.pge.epa.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.repository.TermoEspecificoRepository;
import br.gov.ba.pge.epa.api.service.TermoEspecificoService;

@Service
public class TermoEspecificoServiceImpl implements TermoEspecificoService {

	@Autowired
	private TermoEspecificoRepository repository;
	
	@Override
	public void deleteById(Long id) {
		if (isValidDelete(id)) {
			repository.deleteById(id);
		}
	}

	private boolean isValidDelete(Long id) {
		Optional<TermoEspecifico> optional = repository.findById(id);
		if (optional.isPresent()) {
			// TODO só permitir a exclusão se o TermoEspecifico não estiver vinculado a nenhum Checklist 
			return true;
		}
		return false;
	}

}
