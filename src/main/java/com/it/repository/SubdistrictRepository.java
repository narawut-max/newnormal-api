package com.it.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.SubdistrictEntity;

public interface SubdistrictRepository extends JpaRepository<SubdistrictEntity, Integer>{

	List<SubdistrictEntity> findByZipCode(String zipCode);

}
