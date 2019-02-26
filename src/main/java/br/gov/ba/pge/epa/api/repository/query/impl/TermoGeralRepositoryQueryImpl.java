package br.gov.ba.pge.epa.api.repository.query.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.repository.filter.TermoGeralFilter;
import br.gov.ba.pge.epa.api.repository.query.TermoGeralRepositoryQuery;
import br.gov.ba.pge.epa.api.util.EPAUtil;

public class TermoGeralRepositoryQueryImpl implements TermoGeralRepositoryQuery {

	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TermoGeral> filtrar(TermoGeralFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQuery(clausulasWhere, parametros, filter, "tg");
		
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
	public List<String> buscarNomes(TermoGeralFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQuery(clausulasWhere, parametros, filter, "tg.nome");
		
		Query query = entityManager.createQuery(sql);

		if (!parametros.isEmpty()) {
			parametros.entrySet().forEach(entrySet -> {
				query.setParameter(entrySet.getKey(), entrySet.getValue());
			});
		}
		
		return query.getResultList();
	}

	private String montarQuery(List<String> clausulasWhere, Map<String, Object> parametros, TermoGeralFilter filter, String select) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ").append(select).append(" FROM TermoGeral tg ");
		sql.append("INNER JOIN Checklist AS c on c.termoGeral = tg.id ");
		sql.append("INNER JOIN Nucleo AS n on n.id = c.nucleo ");
		sql.append("INNER JOIN TipoProcesso AS tp on tp.id = c.tipoProcesso ");
		sql.append("INNER JOIN TermoEspecifico AS te on te.id = c.termoEspecifico ");
		sql.append("INNER JOIN Documento AS doc on doc.id = c.documento ");
		sql.append("INNER JOIN Materia AS m on m.id = n.materia ");

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getNome())) {
				clausulasWhere.add("UPPER(tg.nome) LIKE :nome");
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
			
			if (filter.getTermoEspecifico() != null) {
				if (filter.getTermoEspecifico().getId() != null) {
					clausulasWhere.add("te.id = :idTermoEspecifico");
					parametros.put("idTermoEspecifico", filter.getTermoEspecifico().getId());
				}
			}
			
			if (filter.getDocumento() != null) {
				if (filter.getDocumento().getId() != null) {
					clausulasWhere.add("doc.id = :idDocumento");
					parametros.put("idDocumento", filter.getDocumento().getId());
				}
			}
			
			if (filter.getIdMateria() != null) {
				clausulasWhere.add("m.id = :idMateria");
				parametros.put("idMateria", filter.getIdMateria());
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
		sql.append(" ORDER BY tg.nome ASC ");
		
		return sql.toString();
	}

}
