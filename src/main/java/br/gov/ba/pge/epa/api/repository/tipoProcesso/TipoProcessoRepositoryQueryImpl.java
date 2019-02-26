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
import br.gov.ba.pge.epa.api.repository.filter.DocumentoFilter;
import br.gov.ba.pge.epa.api.repository.filter.MateriaFilter;
import br.gov.ba.pge.epa.api.repository.filter.NucleoFilter;
import br.gov.ba.pge.epa.api.repository.filter.TermoEspecificoFilter;
import br.gov.ba.pge.epa.api.repository.filter.TermoGeralFilter;
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
		String sql = montarQuery(clausulasWhere, parametros, filter, "tp");
		
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
		String sql = montarQuery(clausulasWhere, parametros, filter, "tp.nome");
		
		Query query = entityManager.createQuery(sql);

		if (!parametros.isEmpty()) {
			parametros.entrySet().forEach(entrySet -> {
				query.setParameter(entrySet.getKey(), entrySet.getValue());
			});
		}
		
		return query.getResultList();
	}

	private String montarQuery(List<String> clausulasWhere, Map<String, Object> parametros, TipoProcessoFilter filter, String select) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ").append(select).append(" FROM TipoProcesso tp ");
		sql.append("INNER JOIN Checklist AS c on c.tipoProcesso = tp.id ");
		sql.append("INNER JOIN Nucleo AS n on n.id = c.nucleo ");
		sql.append("INNER JOIN TermoGeral AS tg on tg.id = c.termoGeral ");
		sql.append("INNER JOIN TermoEspecifico AS te on te.id = c.termoEspecifico ");
		sql.append("INNER JOIN Documento AS doc on doc.id = c.documento ");
		sql.append("INNER JOIN Materia AS m on m.id = n.materia ");

		if (filter != null) {
			filtrarPorNome(clausulasWhere, parametros, filter.getNome());
			filtrarPorNucleo(clausulasWhere, parametros, filter.getNucleo());
			filtrarPorTermoGeral(clausulasWhere, parametros, filter.getTermoGeral());
			filtrarPorTermoEspecifico(clausulasWhere, parametros, filter.getTermoEspecifico());
			filtrarPorDocumento(clausulasWhere, parametros, filter.getDocumento());
			filtrarPorMateria(clausulasWhere, parametros, filter.getNucleo() != null ? filter.getNucleo().getMateria() : null);
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

	private void filtrarPorNucleo(List<String> clausulasWhere, Map<String, Object> parametros, NucleoFilter nucleo) {
		if (nucleo != null) {
			if (nucleo.getId() != null) {
				clausulasWhere.add("n.id = :idNucleo");
				parametros.put("idNucleo", nucleo.getId());
			}
		}
	}

	private void filtrarPorTermoGeral(List<String> clausulasWhere, Map<String, Object> parametros, TermoGeralFilter termoGeral) {
		if (termoGeral != null) {
			if (termoGeral.getId() != null) {
				clausulasWhere.add("tg.id = :idTermoGeral");
				parametros.put("idTermoGeral", termoGeral.getId());
			}
		}
	}

	private void filtrarPorTermoEspecifico(List<String> clausulasWhere, Map<String, Object> parametros, TermoEspecificoFilter termoEspecifico) {
		if (termoEspecifico != null) {
			if (termoEspecifico.getId() != null) {
				clausulasWhere.add("te.id = :idTermoEspecifico");
				parametros.put("idTermoEspecifico", termoEspecifico.getId());
			}
		}
	}

	private void filtrarPorDocumento(List<String> clausulasWhere, Map<String, Object> parametros, DocumentoFilter documento) {
		if (documento != null) {
			if (documento.getId() != null) {
				clausulasWhere.add("doc.id = :idDocumento");
				parametros.put("idDocumento", documento.getId());
			}
		}
	}

	private void filtrarPorMateria(List<String> clausulasWhere, Map<String, Object> parametros, MateriaFilter materia) {
		if (materia != null) {
			if (materia.getId() != null) {
				clausulasWhere.add("m.id = :idMateria");
				parametros.put("idMateria", materia.getId());
			}
		}
	}

}
