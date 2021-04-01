package com.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String>{

}
