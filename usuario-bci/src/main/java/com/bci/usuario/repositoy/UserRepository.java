package com.bci.usuario.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bci.usuario.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	
	@Query("SELECT u FROM UserEntity u WHERE u.email = ?1 AND u.password = ?2")
	UserEntity findUserByEmailAndPassword(String email, String password);
} 
