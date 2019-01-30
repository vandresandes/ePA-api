package br.gov.ba.pge.epa.api.repository.origem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.gov.ba.pge.epa.api.model.Origem;
import br.gov.ba.pge.epa.api.repository.filter.OrigemFilter;
import br.gov.ba.pge.epa.api.util.EPAUtil;

public class OrigemRepositoryQueryImpl implements OrigemRepositoryQuery {
	
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Origem> filtrar(OrigemFilter filter) {
		List<String> clausulasWhere = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<>();
		String sql = montarQuery(clausulasWhere, parametros, filter, "o");
		
		Query query = entityManager.createQuery(sql);

		if (!parametros.isEmpty()) {
			parametros.entrySet().forEach(entrySet -> {
				query.setParameter(entrySet.getKey(), entrySet.getValue());
			});
		}
		
		return query.getResultList();
	}

	private String montarQuery(List<String> clausulasWhere, Map<String, Object> parametros, OrigemFilter filter, String select) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ").append(select).append(" FROM Origem o ");

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getNome())) {
				clausulasWhere.add("UPPER(o.nome) LIKE :nome");
				parametros.put("nome", "%" + filter.getNome().toUpperCase() + "%");
			}
			if (StringUtils.isNotBlank(filter.getDescricao())) {
				clausulasWhere.add("UPPER(o.descricao) LIKE :descricao");
				parametros.put("descricao", "%" + filter.getDescricao().toUpperCase() + "%");
			}
		}
		
		if (!clausulasWhere.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(EPAUtil.adicionarSeparador(clausulasWhere, " AND "));
		}
		sql.append(" ORDER BY o.nome ASC ");
		
		return sql.toString();
	}

}
