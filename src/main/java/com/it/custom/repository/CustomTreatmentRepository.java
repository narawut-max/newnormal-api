package com.it.custom.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.it.entity.DrugEntity;
import com.it.entity.TreatmentEntity;

@Repository
public class CustomTreatmentRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<TreatmentEntity> searchTreatByCriteria(String billId, String bkId, String userHnId, String userCardId, String userFirstname, String userLastname) {
		List<TreatmentEntity> response = new ArrayList<>();
		StringBuilder sqlBuilder = new StringBuilder();
		
		sqlBuilder.append("SELECT tm.* FROM tb_treatment tm ");
		sqlBuilder.append(" INNER JOIN tb_user user ON user.user_id = tm.user_id ");
		sqlBuilder.append(" WHERE 1 = 1 ");
		
		if (StringUtils.isNotBlank(billId)) {
			sqlBuilder.append("AND tm.bill_id = :billId ");
		}
		
		if (StringUtils.isNotBlank(bkId)) {
			sqlBuilder.append("AND tm.bk_id = :bkId ");
		}
		
		if (StringUtils.isNotBlank(userHnId)) {
			sqlBuilder.append("AND user.user_hn_id = :userHnId ");
		}
		
		if (StringUtils.isNotBlank(userCardId)) {
			sqlBuilder.append("AND user.user_card_id = :userCardId ");
		}
		
		if (StringUtils.isNotBlank(userFirstname)) {
			sqlBuilder.append("AND user.user_firstname like :userFirstname  ");
		}
		
		if (StringUtils.isNotBlank(userLastname)) {
			sqlBuilder.append("AND user.user_lastname like :userLastname  ");
		}
		System.out.println("SQL :: " + sqlBuilder.toString());
		Query query = em.createNativeQuery(sqlBuilder.toString(), TreatmentEntity.class);
		
		if (StringUtils.isNotBlank(billId)) {
			query.setParameter("billId", billId);
		}
		
		if (StringUtils.isNotBlank(bkId)) {
			query.setParameter("bkId", bkId);
		}
		
		if (StringUtils.isNotBlank(userHnId)) {
			query.setParameter("userHnId", userHnId);
		}
		
		if (StringUtils.isNotBlank(userCardId)) {
			query.setParameter("userCardId", userCardId);
		}
		
		if (StringUtils.isNotBlank(userFirstname)) {
			query.setParameter("userFirstname", "%" + userFirstname + "%");
		}
		
		if (StringUtils.isNotBlank(userLastname)) {
			query.setParameter("userLastname", "%" + userLastname + "%");
		}
		
		response = query.getResultList();
		
		return response;
	}
}
