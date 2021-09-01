package com.it.custom.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.it.entity.DrugEntity;
import com.it.entity.UserEntity;

@Repository
public class CustomUserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> searchUserByCriteria(String userId, String userHnId, String userCardId, String userFirstname, String userLastname) {
		List<UserEntity> response = new ArrayList<>();
		StringBuilder sqlBuilder = new StringBuilder();
		
		sqlBuilder.append("SELECT * FROM tb_user");
		sqlBuilder.append(" WHERE 1 = 1 ");
		
		if (StringUtils.isNotBlank(userId)) {
			sqlBuilder.append("AND user_id = :userId ");
		}
		
		if (StringUtils.isNotBlank(userHnId)) {
			sqlBuilder.append("AND user_hn_id = :userHnId ");
		}
		
		if (StringUtils.isNotBlank(userCardId)) {
			sqlBuilder.append("AND user_card_id = :userCardId ");
		}
		
		if (StringUtils.isNotBlank(userFirstname)) {
			sqlBuilder.append("AND user_firstname like :userFirstname  ");
		}
		
		if (StringUtils.isNotBlank(userLastname)) {
			sqlBuilder.append("AND user_lastname like :userLastname  ");
		}
		
		System.out.println("SQL :: " + sqlBuilder.toString());
		Query query = em.createNativeQuery(sqlBuilder.toString(), UserEntity.class);
		
		if (StringUtils.isNotBlank(userId)) {
			query.setParameter("userId", userId);
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
