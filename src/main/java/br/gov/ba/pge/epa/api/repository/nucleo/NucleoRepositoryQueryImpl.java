package br.gov.ba.pge.epa.api.repository.nucleo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.repository.filter.NucleoFilter;
import br.gov.ba.pge.epa.api.util.EPAUtil;

public class NucleoRepositoryQueryImpl implements NucleoRepositoryQuery {
	
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Nucleo> filtrar(NucleoFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQuery(clausulasWhere, parametros, filter, "n");
		
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
	public List<String> buscarNomes(NucleoFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQuery(clausulasWhere, parametros, filter, "n.nome");
		
		Query query = entityManager.createQuery(sql);

		if (!parametros.isEmpty()) {
			parametros.entrySet().forEach(entrySet -> {
				query.setParameter(entrySet.getKey(), entrySet.getValue());
			});
		}
		
		return query.getResultList();
	}

	private String montarQuery(List<String> clausulasWhere, Map<String, Object> parametros, NucleoFilter filter, String select) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ").append(select).append(" FROM Nucleo n ");
		sql.append("INNER JOIN Checklist AS c on c.nucleo = n.id ");
		sql.append("INNER JOIN TipoProcesso AS tp on tp.id = c.tipoProcesso ");
		sql.append("INNER JOIN TermoGeral AS tg on tg.id = c.termoGeral ");
		sql.append("INNER JOIN TermoEspecifico AS te on te.id = c.termoEspecifico ");
		sql.append("INNER JOIN Documento AS doc on doc.id = c.documento ");
		sql.append("INNER JOIN Materia AS m on m.id = n.materia ");

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getNome())) {
				clausulasWhere.add("UPPER(n.nome) LIKE :nome");
				parametros.put("nome", "%" + filter.getNome().toUpperCase() + "%");
			}
			if (filter.getIdTipoProcesso() != null) {
				clausulasWhere.add("tp.id = :idTipoProcesso");
				parametros.put("idTipoProcesso", filter.getIdTipoProcesso());
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
		sql.append(" ORDER BY n.nome ASC ");
		
		return sql.toString();
	}
	
}
