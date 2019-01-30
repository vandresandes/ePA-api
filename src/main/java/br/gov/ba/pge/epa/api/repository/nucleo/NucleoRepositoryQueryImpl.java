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
			if (filter.getId() != null) {
				clausulasWhere.add("n.id = :id");
				parametros.put("id", filter.getId());
			}
			if (StringUtils.isNotBlank(filter.getNome())) {
				clausulasWhere.add("UPPER(n.nome) LIKE :nome");
				parametros.put("nome", "%" + filter.getNome().toUpperCase() + "%");
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
			if (filter.getDocumento() != null) {
				if (filter.getDocumento().getId() != null) {
					clausulasWhere.add("doc.id = :idDocumento");
					parametros.put("idDocumento", filter.getDocumento().getId());
				}
			}
			if (filter.getMateria() != null) {
				if (filter.getMateria().getId() != null) {
					clausulasWhere.add("m.id = :idMateria");
					parametros.put("idMateria", filter.getMateria().getId());
				}
				if (StringUtils.isNotBlank(filter.getMateria().getNome())) {
					clausulasWhere.add("UPPER(m.nome) LIKE :nomeMateria");
					parametros.put("nomeMateria", "%" + filter.getMateria().getNome().toUpperCase() + "%");
				}
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
