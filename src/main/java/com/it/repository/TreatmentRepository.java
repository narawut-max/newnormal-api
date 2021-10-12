package com.it.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.TreatmentEntity;

public interface TreatmentRepository extends JpaRepository<TreatmentEntity, Integer>{

	Optional<List<TreatmentEntity>> findByUserId(Integer userId);

}
