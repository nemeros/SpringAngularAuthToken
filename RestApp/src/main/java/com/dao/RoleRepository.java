package com.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	public List<Role> findByNom(String nom);
}
