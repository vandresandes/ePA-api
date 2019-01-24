package br.gov.ba.pge.epa.api.repository.tipoProcesso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.model.enums.EnumMateria;
import br.gov.ba.pge.epa.api.model.enums.EnumNucleo;
import br.gov.ba.pge.epa.api.model.enums.EnumOrigem;
import br.gov.ba.pge.epa.api.repository.filter.TipoProcessoFilter;
import br.gov.ba.pge.epa.api.util.EPAUtil;

public class TipoProcessoRepositoryQueryImpl implements TipoProcessoRepositoryQuery {

	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoProcesso> filtrar(TipoProcessoFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQueryFiltrar(clausulasWhere, parametros, filter);
		
		Query query = entityManager.createQuery(sql);

		if (!parametros.isEmpty()) {
			parametros.entrySet().forEach(entrySet -> {
				query.setParameter(entrySet.getKey(), entrySet.getValue());
			});
		}
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> buscarNomes(TipoProcessoFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQueryBuscarNomes(clausulasWhere, parametros, filter);
		
		Query query = entityManager.createQuery(sql);

		if (!parametros.isEmpty()) {
			parametros.entrySet().forEach(entrySet -> {
				query.setParameter(entrySet.getKey(), entrySet.getValue());
			});
		}
		
		return query.getResultList();
	}

	private String montarQueryFiltrar(List<String> clausulasWhere, Map<String, Object> parametros, TipoProcessoFilter filter) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT tp FROM TipoProcesso tp ");
		sql.append("INNER JOIN Checklist AS c on c.tipoProcesso = tp.id ");
		sql.append("INNER JOIN Nucleo AS n on n.id = c.nucleo ");
		sql.append("INNER JOIN TermoGeral AS tg on tg.id = c.termoGeral ");
		sql.append("INNER JOIN TermoEspecifico AS te on te.id = c.termoEspecifico ");
		sql.append("INNER JOIN Documento AS doc on doc.id = c.documento ");
		sql.append("INNER JOIN Materia AS m on m.id = n.materia ");

		if (filter != null) {
			filtrarPorNome(clausulasWhere, parametros, filter.getNome());
			filtrarPorIdNucleo(clausulasWhere, parametros, filter.getIdNucleo());
			filtrarPorIdTermoGeral(clausulasWhere, parametros, filter.getIdTermoGeral());
			filtrarPorIdTermoEspecifico(clausulasWhere, parametros, filter.getIdTermoEspecifico());
			filtrarPorIdDocumento(clausulasWhere, parametros, filter.getIdDocumento());
			filtrarPorIdMateria(clausulasWhere, parametros, filter.getIdMateria());
			filtrarPorIdOrigem(clausulasWhere, parametros, filter);
		}
		
		if (!clausulasWhere.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(EPAUtil.adicionarSeparador(clausulasWhere, " AND "));
		}
		sql.append(" ORDER BY tp.nome ASC ");
		
		return sql.toString();
	}

	private String montarQueryBuscarNomes(List<String> clausulasWhere, Map<String, Object> parametros, TipoProcessoFilter filter) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT tp.nome FROM TipoProcesso tp ");
		sql.append("INNER JOIN Checklist AS c on c.tipoProcesso = tp.id ");
		sql.append("INNER JOIN Nucleo AS n on n.id = c.nucleo ");
		sql.append("INNER JOIN TermoGeral AS tg on tg.id = c.termoGeral ");
		sql.append("INNER JOIN TermoEspecifico AS te on te.id = c.termoEspecifico ");
		sql.append("INNER JOIN Documento AS doc on doc.id = c.documento ");
		sql.append("INNER JOIN Materia AS m on m.id = n.materia ");

		if (filter != null) {
			filtrarPorNome(clausulasWhere, parametros, filter.getNome());
			filtrarPorIdNucleo(clausulasWhere, parametros, filter.getIdNucleo());
			filtrarPorIdTermoGeral(clausulasWhere, parametros, filter.getIdTermoGeral());
			filtrarPorIdTermoEspecifico(clausulasWhere, parametros, filter.getIdTermoEspecifico());
			filtrarPorIdDocumento(clausulasWhere, parametros, filter.getIdDocumento());
			filtrarPorIdMateria(clausulasWhere, parametros, filter.getIdMateria());
		}
		
		if (!clausulasWhere.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(EPAUtil.adicionarSeparador(clausulasWhere, " AND "));
		}
		sql.append(" ORDER BY tp.nome ASC ");
		
		return sql.toString();
	}

	private void filtrarPorNome(List<String> clausulasWhere, Map<String, Object> parametros, String nome) {
		if (StringUtils.isNotBlank(nome)) {
			clausulasWhere.add("UPPER(tp.nome) LIKE :nome");
			parametros.put("nome", "%" + nome.toUpperCase() + "%");
		}
	}

	private void filtrarPorIdNucleo(List<String> clausulasWhere, Map<String, Object> parametros, Long idNucleo) {
		if (idNucleo != null) {
			clausulasWhere.add("n.id = :idNucleo");
			parametros.put("idNucleo", idNucleo);
		}
	}

	private void filtrarPorIdTermoGeral(List<String> clausulasWhere, Map<String, Object> parametros, Long idTermoGeral) {
		if (idTermoGeral != null) {
			clausulasWhere.add("tg.id = :idTermoGeral");
			parametros.put("idTermoGeral", idTermoGeral);
		}
	}

	private void filtrarPorIdTermoEspecifico(List<String> clausulasWhere, Map<String, Object> parametros, Long idTermoEspecifico) {
		if (idTermoEspecifico != null) {
			clausulasWhere.add("te.id = :idTermoEspecifico");
			parametros.put("idTermoEspecifico", idTermoEspecifico);
		}
	}

	private void filtrarPorIdDocumento(List<String> clausulasWhere, Map<String, Object> parametros, Long idDocumento) {
		if (idDocumento != null) {
			clausulasWhere.add("doc.id = :idDocumento");
			parametros.put("idDocumento", idDocumento);
		}
	}

	private void filtrarPorIdMateria(List<String> clausulasWhere, Map<String, Object> parametros, Long idMateria) {
		if (idMateria != null) {
			clausulasWhere.add("m.id = :idMateria");
			parametros.put("idMateria", idMateria);
		}
	}

	private void filtrarPorIdOrigem(List<String> clausulasWhere, Map<String, Object> parametros, TipoProcessoFilter filter) {
		if (filter.getIdMateria() != null && filter.getIdOrigem() != null && filter.getIdNucleo() == null) {
			if (EnumMateria.LICITACOES_E_CONTRATOS.getValor().equals(filter.getIdMateria())) {
				EnumOrigem origem = EnumOrigem.buscarPeloValor(filter.getIdOrigem());
				Long idNucleo = null;
				
				switch (origem) {
				case SESAB:
					idNucleo = EnumNucleo.NSESAB.getValor();
					break;
				case SEC:
					idNucleo = EnumNucleo.NSAS.getValor();
					break;
				case SEPROMI:
					idNucleo = EnumNucleo.NSAS.getValor();
					break;
				case SJDHDS:
					idNucleo = EnumNucleo.NSAS.getValor();
					break;
				case PMBA:
					idNucleo = EnumNucleo.NSSP.getValor();
					break;
				case PCBA:
					idNucleo = EnumNucleo.NSSP.getValor();
					break;
				case SSP:
					idNucleo = EnumNucleo.NSSP.getValor();
					break;
				case CBMBA:
					idNucleo = EnumNucleo.NSSP.getValor();
					break;
				default:
					idNucleo = EnumNucleo.NLC.getValor();
					break;
				}
				filtrarPorIdNucleo(clausulasWhere, parametros, idNucleo);
			}
		}
	}

}
