package br.gov.ba.pge.epa.api.repository.tipoProcesso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.ba.pge.epa.api.model.TipoProcesso;
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
			if (filter.getIdNucleo() != null) {
				clausulasWhere.add("n.id = :idNucleo");
				parametros.put("idNucleo", filter.getIdNucleo());
			}
			if (filter.getIdTermoGeral() != null) {
				clausulasWhere.add("tg.id = :idTermoGeral");
				parametros.put("idTermoGeral", filter.getIdTermoGeral());
			}
			if (filter.getIdTermoEspecifico() != null) {
				clausulasWhere.add("te.id = :idTermoEspecifico");
				parametros.put("idTermoEspecifico", filter.getIdTermoEspecifico());
			}
			if (filter.getIdDocumento() != null) {
				clausulasWhere.add("doc.id = :idDocumento");
				parametros.put("idDocumento", filter.getIdDocumento());
			}
			if (filter.getIdMateria() != null) {
				clausulasWhere.add("m.id = :idMateria");
				parametros.put("idMateria", filter.getIdMateria());
			}
		}
		
		if (!clausulasWhere.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(EPAUtil.adicionarSeparador(clausulasWhere, " AND "));
		}
		return sql.toString();
	}

	@Override
	public List<String> findAllNomes() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<TipoProcesso> root = cq.from(TipoProcesso.class);

		cq.select(root.get("nome"));
		cq.orderBy(cb.asc(root.get("nome")));

		TypedQuery<String> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

}
