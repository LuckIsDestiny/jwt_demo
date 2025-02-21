package com.jwt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.demo.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {


}
