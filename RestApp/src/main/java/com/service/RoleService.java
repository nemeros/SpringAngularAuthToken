package com.service;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.RoleRepository;
import com.entity.Role;

@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class RoleService {

	@Autowired
	private RoleRepository roleDao;
	
	/**
	 * GetAll
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Iterable<Role>> getAllRoles(){
		return new ResponseEntity<Iterable<Role>>(roleDao.findAll(), HttpStatus.OK);
	}
	
	/**
	 * GetOne
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Role> getOneRole(@PathVariable long id){
		return new ResponseEntity<Role>(roleDao.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Role>> findRolesByNom(@RequestParam(required=true, name="nom") String nom){
		return new ResponseEntity<Iterable<Role>>(roleDao.findByNom(nom), HttpStatus.OK);
	}
	
	/**
	 * Update
	 * @param role
	 * @return
	 */
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<Role> updateRole(@Valid @RequestBody(required=true) Role role){
		roleDao.save(role);
		return new ResponseEntity<Role>(roleDao.findOne(role.getId()), HttpStatus.CREATED);
	}
	
	/**
	 * Creation
	 * @param role
	 * @return
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Role> createRole(@Valid @RequestBody(required=true) Role role, Principal p){
		roleDao.save(role);		
		return new ResponseEntity<Role>(roleDao.findOne(role.getId()), HttpStatus.CREATED);
	}
}
