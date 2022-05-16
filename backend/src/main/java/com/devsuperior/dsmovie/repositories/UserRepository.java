package com.devsuperior.dsmovie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsmovie.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	@Query(nativeQuery = true, value = "select * from tb_user limit :limit")
	public List<User> buscaLimite(Integer limit);
}