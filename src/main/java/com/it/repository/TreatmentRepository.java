package com.it.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.TreatmentEntity;

public interface TreatmentRepository extends JpaRepository<TreatmentEntity, Integer>{

	Optional<TreatmentEntity> findByUserId(Integer userId);

}
