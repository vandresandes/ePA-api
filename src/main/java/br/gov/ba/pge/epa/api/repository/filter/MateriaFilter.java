package br.gov.ba.pge.epa.api.repository.filter;

import javax.persistence.Transient;

import br.gov.ba.pge.epa.api.model.enums.EnumMateria;
import br.gov.ba.pge.epa.api.model.enums.EnumNucleo;
import br.gov.ba.pge.epa.api.model.enums.EnumOrigem;

public class MateriaFilter {

	private Long id;
	private String nome;
	private Long idNucleo;
	private Long idTipoProcesso;
	private Long idTermoGeral;
	private Long idTermoEspecifico;
	private Long idDocumento;
	private OrgaoFilter origem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdNucleo() {
		return idNucleo;
	}

	public void setIdNucleo(Long idNucleo) {
		this.idNucleo = idNucleo;
	}

	public Long getIdTipoProcesso() {
		return idTipoProcesso;
	}

	public void setIdTipoProcesso(Long idTipoProcesso) {
		this.idTipoProcesso = idTipoProcesso;
	}

	public Long getIdTermoGeral() {
		return idTermoGeral;
	}

	public void setIdTermoGeral(Long idTermoGeral) {
		this.idTermoGeral = idTermoGeral;
	}

	public Long getIdTermoEspecifico() {
		return idTermoEspecifico;
	}

	public void setIdTermoEspecifico(Long idTermoEspecifico) {
		this.idTermoEspecifico = idTermoEspecifico;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public OrgaoFilter getOrigem() {
		return origem;
	}

	public void setOrigem(OrgaoFilter origem) {
		this.origem = origem;
	}

	@Transient
	public Long obterIdNucleo() {
		if (getId() != null && EnumMateria.LICITACOES_E_CONTRATOS.getValor().equals(getId()) && (getOrigem() != null && getOrigem().getId() != null)) {
			EnumOrigem origem = EnumOrigem.buscarPeloValor(getOrigem().getId());
			switch (origem) {
			case SESAB:
				return EnumNucleo.NSESAB.getValor();
			case SEC:
				return EnumNucleo.NSAS.getValor();
			case SEPROMI:
				return EnumNucleo.NSAS.getValor();
			case SJDHDS:
				return EnumNucleo.NSAS.getValor();
			case PMBA:
				return EnumNucleo.NSSP.getValor();
			case PCBA:
				return EnumNucleo.NSSP.getValor();
			case SSP:
				return EnumNucleo.NSSP.getValor();
			case CBMBA:
				return EnumNucleo.NSSP.getValor();
			default:
				return EnumNucleo.NLC.getValor();
			}
		}
		return null;
	}

}
