package br.gov.ba.pge.epa.api.service.sei;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.client.facade.ISei;
import br.gov.ba.pge.client.servico.FabricaSei;
import br.gov.ba.pge.client.vo.RetornoConsultaDocumentoVO;
import br.gov.ba.pge.client.vo.RetornoConsultaProcedimentoVO;
import br.gov.ba.pge.enums.EnumParametrosSistema;
import br.gov.ba.pge.epa.api.util.EPAUtil;


@Service
public class SeiServiceImpl implements SeiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SeiServiceImpl.class);
	private static final String N = "N";
	
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
			String consultaNumeroProcedimento = EPAUtil.removerCaracteresNaoNumericos(consultarDocumento.getProcedimentoFormatado());
			return protocoloProcedimento.equals(consultaNumeroProcedimento);
		}
		return false;
	}

	@Override
	public RetornoConsultaProcedimentoVO consultarProtocolo(String protocoloProcedimento) {
        try {
            return sei.consultarProcedimento( 
            		EnumParametrosSistema.SIGLA_SISTEMA.getValor(),
                    EnumParametrosSistema.IDENTIFICACAO_SERVICO.getValor(),
                    EnumParametrosSistema.ID_UNIDADE.getValor(),
                    protocoloProcedimento,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
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