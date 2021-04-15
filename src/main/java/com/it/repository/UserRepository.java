package com.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	public UserEntity findByUserUsername(String username);

	public UserEntity findByUserCardId(String userCardId);
}
