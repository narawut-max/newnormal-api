package com.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, String>{
	
}
