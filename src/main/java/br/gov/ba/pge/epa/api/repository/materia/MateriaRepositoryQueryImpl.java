package br.gov.ba.pge.epa.api.repository.materia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.gov.ba.pge.epa.api.model.Materia;
import br.gov.ba.pge.epa.api.repository.filter.MateriaFilter;
import br.gov.ba.pge.epa.api.util.EPAUtil;

public class MateriaRepositoryQueryImpl implements MateriaRepositoryQuery {

	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Materia> filtrar(MateriaFilter filter) {
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
	public List<String> buscarNomes(MateriaFilter filter) {
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

	private String montarQueryFiltrar(List<String> clausulasWhere, Map<String, Object> parametros, MateriaFilter filter) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT m FROM Materia m ");
		sql.append("INNER JOIN Nucleo AS n on n.materia = m.id ");
		sql.append("INNER JOIN Checklist AS c on c.nucleo = n.id ");
		sql.append("INNER JOIN TipoProcesso AS tp on tp.id = c.tipoProcesso ");
		sql.append("INNER JOIN TermoGeral AS tg on tg.id = c.termoGeral ");
		sql.append("INNER JOIN TermoEspecifico AS te on te.id = c.termoEspecifico ");
		sql.append("INNER JOIN Documento AS doc on doc.id = c.documento ");

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getNome())) {
				clausulasWhere.add("UPPER(m.nome) LIKE :nome");
				parametros.put("nome", "%" + filter.getNome().toUpperCase() + "%");
			}
			if (filter.getIdNucleo() != null) {
				clausulasWhere.add("n.id = :idNucleo");
				parametros.put("idNucleo", filter.getIdNucleo());
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
		}
		
		if (!clausulasWhere.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(EPAUtil.adicionarSeparador(clausulasWhere, " AND "));
		}
		sql.append(" ORDER BY m.nome ASC ");
		
		return sql.toString();
	}

	private String montarQueryBuscarNomes(List<String> clausulasWhere, Map<String, Object> parametros, MateriaFilter filter) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT m.nome FROM Materia m ");
		sql.append("INNER JOIN Nucleo AS n on n.materia = m.id ");
		sql.append("INNER JOIN Checklist AS c on c.nucleo = n.id ");
		sql.append("INNER JOIN TipoProcesso AS tp on tp.id = c.tipoProcesso ");
		sql.append("INNER JOIN TermoGeral AS tg on tg.id = c.termoGeral ");
		sql.append("INNER JOIN TermoEspecifico AS te on te.id = c.termoEspecifico ");
		sql.append("INNER JOIN Documento AS doc on doc.id = c.documento ");

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getNome())) {
				clausulasWhere.add("UPPER(m.nome) LIKE :nome");
				parametros.put("nome", "%" + filter.getNome().toUpperCase() + "%");
			}
			if (filter.getIdNucleo() != null) {
				clausulasWhere.add("n.id = :idNucleo");
				parametros.put("idNucleo", filter.getIdNucleo());
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
		}
		
		if (!clausulasWhere.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(EPAUtil.adicionarSeparador(clausulasWhere, " AND "));
		}
		sql.append(" ORDER BY m.nome ASC ");
		
		return sql.toString();
	}

}
