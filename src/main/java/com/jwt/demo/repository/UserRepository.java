package com.jwt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByUsername(String username);
}
