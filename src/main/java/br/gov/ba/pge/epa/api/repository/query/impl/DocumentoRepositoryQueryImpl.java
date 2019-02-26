package br.gov.ba.pge.epa.api.repository.query.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.repository.filter.DocumentoFilter;
import br.gov.ba.pge.epa.api.repository.query.DocumentoRepositoryQuery;
import br.gov.ba.pge.epa.api.util.EPAUtil;

public class DocumentoRepositoryQueryImpl implements DocumentoRepositoryQuery {
	
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Documento> filtrar(DocumentoFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQuery(clausulasWhere, parametros, filter, "doc");

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
	public List<String> buscarNomes(DocumentoFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQuery(clausulasWhere, parametros, filter, "doc.nome");
		
		Query query = entityManager.createQuery(sql);

		if (!parametros.isEmpty()) {
			parametros.entrySet().forEach(entrySet -> {
				query.setParameter(entrySet.getKey(), entrySet.getValue());
			});
		}
		
		return query.getResultList();
	}

	private String montarQuery(List<String> clausulasWhere, Map<String, Object> parametros, DocumentoFilter filter, String select) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ").append(select).append(" FROM Documento doc ");
		sql.append("INNER JOIN Checklist AS c on c.documento = doc.id ");
		sql.append("INNER JOIN Nucleo AS n on n.id = c.nucleo ");
		sql.append("INNER JOIN TipoProcesso AS tp on tp.id = c.tipoProcesso ");
		sql.append("INNER JOIN TermoGeral AS tg on tg.id = c.termoGeral ");
		sql.append("INNER JOIN TermoEspecifico AS te on te.id = c.termoEspecifico ");
		sql.append("INNER JOIN Materia AS m on m.id = n.materia ");

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getNome())) {
				clausulasWhere.add("UPPER(doc.nome) LIKE :nome");
				parametros.put("nome", "%" + filter.getNome().toUpperCase() + "%");
			}
			
			if (filter.getTipo() != null) {
				clausulasWhere.add("doc.tipo = :tipo");
				parametros.put("tipo", filter.getTipo());
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
			
			if (filter.getTermoEspecifico() != null) {
				if (filter.getTermoEspecifico().getId() != null) {
					clausulasWhere.add("te.id = :idTermoEspecifico");
					parametros.put("idTermoEspecifico", filter.getTermoEspecifico().getId());
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
		sql.append(" ORDER BY doc.nome ASC ");
		
		return sql.toString();
	}

}
