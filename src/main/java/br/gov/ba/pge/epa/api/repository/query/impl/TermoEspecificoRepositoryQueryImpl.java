package br.gov.ba.pge.epa.api.repository.query.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.repository.filter.TermoEspecificoFilter;
import br.gov.ba.pge.epa.api.repository.query.TermoEspecificoRepositoryQuery;
import br.gov.ba.pge.epa.api.util.EPAUtil;

public class TermoEspecificoRepositoryQueryImpl implements TermoEspecificoRepositoryQuery {

	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TermoEspecifico> filtrar(TermoEspecificoFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQuery(clausulasWhere, parametros, filter, "te");
		
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
	public List<String> buscarNomes(TermoEspecificoFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQuery(clausulasWhere, parametros, filter, "te.nome");
		
		Query query = entityManager.createQuery(sql);

		if (!parametros.isEmpty()) {
			parametros.entrySet().forEach(entrySet -> {
				query.setParameter(entrySet.getKey(), entrySet.getValue());
			});
		}
		
		return query.getResultList();
	}

	private String montarQuery(List<String> clausulasWhere, Map<String, Object> parametros, TermoEspecificoFilter filter, String select) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ").append(select).append(" FROM TermoEspecifico te ");
		sql.append("INNER JOIN Checklist AS c on c.termoEspecifico = te.id ");
		sql.append("INNER JOIN Nucleo AS n on n.id = c.nucleo ");
		sql.append("INNER JOIN TipoProcesso AS tp on tp.id = c.tipoProcesso ");
		sql.append("INNER JOIN TermoGeral AS tg on tg.id = c.termoGeral ");
		sql.append("INNER JOIN Documento AS doc on doc.id = c.documento ");
		sql.append("INNER JOIN Materia AS m on m.id = n.materia ");

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getNome())) {
				clausulasWhere.add("UPPER(te.nome) LIKE :nome");
				parametros.put("nome", "%" + filter.getNome().toUpperCase() + "%");
			}
			
			if (filter.getNucleo() != null) {
				if (filter.getNucleo().getId() != null) {
					clausulasWhere.add("n.id = :idNucleo");
					parametros.put("idNucleo", filter.getNucleo().getId());
				}
			}
			
			if (filter.getTipoProcesso() != null) {
				if (filter.getTipoProcesso().getId() != null) {
					clausulasWhere.add("tp.id = :idTipoProcesso");
					parametros.put("idTipoProcesso", filter.getTipoProcesso().getId());
				}
			}
			
			if (filter.getTermoGeral() != null) {
				if (filter.getTermoGeral().getId() != null) {
					clausulasWhere.add("tg.id = :idTermoGeral");
					parametros.put("idTermoGeral", filter.getTermoGeral().getId());
				}
			}
			
			if (filter.getDocumento() != null) {
				if (filter.getDocumento().getId() != null) {
					clausulasWhere.add("doc.id = :idDocumento");
					parametros.put("idDocumento", filter.getDocumento().getId());
				}
			}
			
			if (filter.getNucleo() != null) {
				if (filter.getNucleo().getId() != null) {
					clausulasWhere.add("n.id = :idNucleo");
					parametros.put("idNucleo", filter.getNucleo().getId());
				}
				if (filter.getNucleo().getMateria() != null) {
					if (filter.getNucleo().getMateria().getId() != null) {
						clausulasWhere.add("m.id = :idMateria");
						parametros.put("idMateria", filter.getNucleo().getMateria().getId());
					}
				}
			}
		}
		
		if (!clausulasWhere.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(EPAUtil.adicionarSeparador(clausulasWhere, " AND "));
		}
		sql.append(" ORDER BY te.nome ASC ");
		
		return sql.toString();
	}

}
