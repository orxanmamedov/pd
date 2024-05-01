package com.orkhanmamedov.expressbank.repository;

import com.orkhanmamedov.expressbank.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {}
