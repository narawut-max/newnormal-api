package com.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.TreatmentEntity;

public interface TreatmentRepository extends JpaRepository<TreatmentEntity, String>{

}
