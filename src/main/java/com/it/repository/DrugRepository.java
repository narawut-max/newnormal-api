package com.it.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.DrugEntity;

public interface DrugRepository extends JpaRepository<DrugEntity, String>{

	Optional<DrugEntity> findByDrugName(String drugName);

}
