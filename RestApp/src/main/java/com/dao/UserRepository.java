package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("select u from User u where u.role.nom = ?1")
	public List<User> findByRoleNom(String roleNom);
	
	@Query(value="select id, nom, id_role from t_user where nom = ?1", nativeQuery = true)
	public List<User> findByNative(String nom);
}
