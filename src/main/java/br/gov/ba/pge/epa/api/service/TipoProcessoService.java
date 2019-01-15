package br.gov.ba.pge.epa.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.repository.TipoProcessoRepository;

@Service
public class TipoProcessoService {

	@Autowired
	private TipoProcessoRepository repository;
}
