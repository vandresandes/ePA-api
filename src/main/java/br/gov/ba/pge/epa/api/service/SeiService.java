package br.gov.ba.pge.epa.api.service;

import br.gov.ba.pge.client.vo.RetornoConsultaDocumentoVO;
import br.gov.ba.pge.client.vo.RetornoConsultaProcedimentoVO;

public interface SeiService {

	// buscas na base do SEI

	public RetornoConsultaProcedimentoVO consultarProtocolo(String protocoloProcedimento);

	public RetornoConsultaDocumentoVO consultarDocumento(String protocoloDocumento);

	// validações na base do SEI

	public boolean existeProtocolo(String numeroProcesso);

	public boolean existeDocumento(String numeroProcesso, String protocoloDocumento);

	public int contarProcessoAbertoEmOutrasUnidades(String protocoloProcedimento);
}
