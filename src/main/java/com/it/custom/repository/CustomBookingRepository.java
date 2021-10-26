package com.it.custom.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.it.entity.BookingEntity;
@Repository
public class CustomBookingRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<BookingEntity> searchTreatByCriteria(String bkId, String userHnId, String userFirstname, String userLastname) {
		List<BookingEntity> response = new ArrayList<>();
		StringBuilder sqlBuilder = new StringBuilder();
		
		sqlBuilder.append("SELECT bk.* FROM tb_booking bk ");
		sqlBuilder.append(" INNER JOIN tb_user user ON user.user_id = bk.user_id ");
		sqlBuilder.append(" WHERE 1 = 1 ");

		if (StringUtils.isNotBlank(bkId)) {
			sqlBuilder.append("AND bk.bk_id = :bkId ");
		}
		
		if (StringUtils.isNotBlank(userHnId)) {
			sqlBuilder.append("AND user.user_hn_id = :userHnId ");
		}
		
		if (StringUtils.isNotBlank(userFirstname)) {
			sqlBuilder.append("AND user.user_firstname like :userFirstname  ");
		}
		
		if (StringUtils.isNotBlank(userLastname)) {
			sqlBuilder.append("AND user.user_lastname like :userLastname  ");
		}
		System.out.println("SQL :: " + sqlBuilder.toString());
		Query query = em.createNativeQuery(sqlBuilder.toString(), BookingEntity.class);
		
		if (StringUtils.isNotBlank(bkId)) {
			query.setParameter("bkId", bkId);
		}
		
		if (StringUtils.isNotBlank(userHnId)) {
			query.setParameter("userHnId", userHnId);
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
