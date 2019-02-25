package br.gov.ba.pge.epa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.client.vo.RetornoConsultaDocumentoVO;
import br.gov.ba.pge.client.vo.RetornoConsultaProcedimentoVO;
import br.gov.ba.pge.epa.api.service.SeiService;

@RestController
@RequestMapping("/sei")
public class SeiController {

	@Autowired
	private SeiService seiService;

	@GetMapping("/existeDocumento")
	public ResponseEntity<Boolean> existeDocumento(String protocoloProcedimento, String protocoloDocumento) {
		return ResponseEntity.ok(seiService.existeDocumento(protocoloProcedimento, protocoloDocumento));
	}

	@GetMapping("/existeProtocolo")
	public ResponseEntity<Boolean> existeProtocolo(String protocoloProcedimento) {
		return ResponseEntity.ok(seiService.existeProtocolo(protocoloProcedimento));
	}

	@GetMapping("/contarProcessoAbertoEmOutrasUnidades")
	public ResponseEntity<Integer> contarProcessoAbertoEmOutrasUnidades(String protocoloProcedimento) {
		return ResponseEntity.ok(seiService.contarProcessoAbertoEmOutrasUnidades(protocoloProcedimento));
	}

	@GetMapping("/consultarDocumento")
	public ResponseEntity<RetornoConsultaDocumentoVO> consultarDocumento(String protocoloDocumento) {
		RetornoConsultaDocumentoVO retornoConsultaDocumentoVO = seiService.consultarDocumento(protocoloDocumento);
		return retornoConsultaDocumentoVO != null ? ResponseEntity.ok(retornoConsultaDocumentoVO) : ResponseEntity.notFound().build();
	}

	@GetMapping("/consultarProtocolo")
	public ResponseEntity<RetornoConsultaProcedimentoVO> consultarProtocolo(String protocoloProcedimento) {
		RetornoConsultaProcedimentoVO retornoConsultaProcedimentoVO = seiService.consultarProtocolo(protocoloProcedimento);
		return retornoConsultaProcedimentoVO != null ? ResponseEntity.ok(retornoConsultaProcedimentoVO) : ResponseEntity.notFound().build();
	}

}
