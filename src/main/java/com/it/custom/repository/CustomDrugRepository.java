package com.it.custom.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.it.entity.DrugEntity;

@Repository
public class CustomDrugRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<DrugEntity> searchDrugByCriteria(String drugId, String drugName, String drugTrademark) {
		List<DrugEntity> response = new ArrayList<>();
		StringBuilder sqlBuilder = new StringBuilder();
		
		sqlBuilder.append("SELECT * FROM tb_drug");
		sqlBuilder.append(" WHERE 1 = 1 ");
		
		if (StringUtils.isNotBlank(drugId)) {
			sqlBuilder.append("AND drug_id = :drugId ");
		}
		
		if (StringUtils.isNotBlank(drugName)) {
			sqlBuilder.append("AND drug_name = :drugName ");
		}
		
		if (StringUtils.isNotBlank(drugTrademark)) {
			sqlBuilder.append("AND drug_trademark = :drugTrademark ");
		}
		System.out.println("SQL :: " + sqlBuilder.toString());
		Query query = em.createNativeQuery(sqlBuilder.toString(), DrugEntity.class);
		
		if (StringUtils.isNotBlank(drugId)) {
			query.setParameter("drugId", drugId);
		}
		
		if (StringUtils.isNotBlank(drugName)) {
			query.setParameter("drugName", drugName);
		}
		
		if (StringUtils.isNotBlank(drugTrademark)) {
			query.setParameter("drugTrademark", drugTrademark);
		}
		
		response = query.getResultList();
		
		return response;
	}

}
