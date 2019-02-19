package br.gov.ba.pge.epa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.client.vo.RetornoConsultaDocumentoVO;
import br.gov.ba.pge.client.vo.RetornoConsultaProcedimentoVO;
import br.gov.ba.pge.epa.api.service.sei.SeiService;

@RestController
@RequestMapping("/sei")
public class SeiController {

	@Autowired
	private SeiService seiService;

	@GetMapping("/existeDocumento")
	public ResponseEntity<Boolean> existeDocumento(String protocoloProcedimento, String protocoloDocumento) {
		return seiService.existeDocumento(protocoloProcedimento, protocoloDocumento) ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
	}

	@GetMapping("/existeProtocolo")
	public ResponseEntity<Boolean> existeProtocolo(String protocoloProcedimento) {
		return seiService.existeProtocolo(protocoloProcedimento) ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
	}

	@GetMapping("/consultarProtocolo")
	public ResponseEntity<RetornoConsultaProcedimentoVO> consultarProtocolo(String protocoloProcedimento) {
		RetornoConsultaProcedimentoVO retornoConsultaProcedimentoVO = seiService.consultarProtocolo(protocoloProcedimento);
		return retornoConsultaProcedimentoVO != null ? ResponseEntity.ok(retornoConsultaProcedimentoVO) : ResponseEntity.notFound().build();
	}

	@GetMapping("/consultarDocumento")
	public ResponseEntity<RetornoConsultaDocumentoVO> consultarDocumento(String protocoloDocumento) {
		RetornoConsultaDocumentoVO retornoConsultaDocumentoVO = seiService.consultarDocumento(protocoloDocumento);
		return retornoConsultaDocumentoVO != null ? ResponseEntity.ok(retornoConsultaDocumentoVO) : ResponseEntity.notFound().build();
	}

}
