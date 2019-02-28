package br.gov.ba.pge.epa.api.service.impl;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.client.facade.ISei;
import br.gov.ba.pge.client.servico.FabricaSei;
import br.gov.ba.pge.client.vo.RetornoConsultaDocumentoVO;
import br.gov.ba.pge.client.vo.RetornoConsultaProcedimentoVO;
import br.gov.ba.pge.epa.api.model.enums.EnumParametrosSistema;
import br.gov.ba.pge.epa.api.service.SeiService;
import br.gov.ba.pge.epa.api.util.EPAUtil;


@Service
public class SeiServiceImpl implements SeiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SeiServiceImpl.class);
	private static final String N = "N";
	private static final String S = "S";
	
	private ISei sei = FabricaSei.getInstance().obterSei();

	@Override
	public boolean existeProtocolo(String protocoloProcedimento) {
        RetornoConsultaProcedimentoVO consultarProcedimento = this.consultarProtocolo(protocoloProcedimento);
        return consultarProcedimento != null && consultarProcedimento.getProcedimentoFormatado() != null;
    }

	@Override
	public boolean existeDocumento(String protocoloProcedimento, String protocoloDocumento) {
		RetornoConsultaDocumentoVO consultarDocumento = consultarDocumento(protocoloDocumento);
		if (consultarDocumento != null) {
			String responseNumeroProcedimento = EPAUtil.removerCaracteresNaoNumericos(consultarDocumento.getProcedimentoFormatado());
			protocoloProcedimento = EPAUtil.removerCaracteresNaoNumericos(protocoloProcedimento);
			return protocoloProcedimento.equals(responseNumeroProcedimento);
		}
		return false;
	}

	@Override
	public int contarProcessoAbertoEmOutrasUnidades(String protocoloProcedimento) {
		RetornoConsultaProcedimentoVO consultarProcedimento = this.consultarProtocolo(protocoloProcedimento);
		return consultarProcedimento != null ? consultarProcedimento.getUnidadesProcedimentoAberto().length : 0;
	}

	@Override
	public RetornoConsultaProcedimentoVO consultarProtocolo(String protocoloProcedimento) {
        try {
            return sei.consultarProcedimento( 
            		EnumParametrosSistema.SIGLA_SISTEMA.getValor(),
                    EnumParametrosSistema.IDENTIFICACAO_SERVICO.getValor(),
                    EnumParametrosSistema.ID_UNIDADE.getValor(),
                    protocoloProcedimento,
                    S,
                    S,
                    S,
                    S,
                    S,
                    S,
                    S,
                    S,
                    S);
        } catch (RemoteException ex) {
        	LOGGER.debug(ex.getMessage());
        }
        return null;
    }

	@Override
	public RetornoConsultaDocumentoVO consultarDocumento(String numeroDocumento) {
        try {
            return sei.consultarDocumento(
            		EnumParametrosSistema.SIGLA_SISTEMA.getValor(),
            		EnumParametrosSistema.IDENTIFICACAO_SERVICO.getValor(),
            		EnumParametrosSistema.ID_UNIDADE.getValor(),
            		numeroDocumento,
                    N, N, N);
        } catch (RemoteException ex) {
        	LOGGER.debug(ex.getMessage());
        }
        return null;
    }

}
