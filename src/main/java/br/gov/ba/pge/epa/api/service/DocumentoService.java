package br.gov.ba.pge.epa.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.repository.DocumentoRepository;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository repository;
	
	public void deleteById(Long id) {
		if (isValidDelete(id)) {
			repository.deleteById(id);
		}
	}

	private boolean isValidDelete(Long id) {
		Optional<Documento> optional = repository.findById(id);
		if (optional.isPresent()) {
			// TODO só permitir a exclusão se o Documento não estiver vinculado a nenhum Checklist 
			return true;
		}
		return false;
	}

}
