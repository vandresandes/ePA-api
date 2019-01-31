package br.gov.ba.pge.epa.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.repository.TipoProcessoRepository;

@Service
public class TipoProcessoService {

	@Autowired
	private TipoProcessoRepository repository;

	public void deleteById(Long id) {
		if (isValidDelete(id)) {
			repository.deleteById(id);
		}
	}

	private boolean isValidDelete(Long id) {
		Optional<TipoProcesso> optional = repository.findById(id);
		if (optional.isPresent()) {
			// TODO só permitir a exclusão se o TipoProcesso não estiver vinculado a nenhum Checklist 
			return true;
		}
		return false;
	}

}
