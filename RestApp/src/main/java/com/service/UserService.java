package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserRepository;
import com.entity.User;

@RestController
@RequestMapping("/api/user")
public class UserService {
	
	@Autowired
	private UserRepository userDao;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getAllUser(){		
		return new ResponseEntity<Iterable<User>>(userDao.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getSingleUser(@PathVariable("id") long id){		
		return new ResponseEntity<User>(userDao.findOne(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public ResponseEntity<Iterable<User>> findByRoleNom(@RequestParam(required=true,name="roleNom") String roleNom){		
		return new ResponseEntity<Iterable<User>>(userDao.findByRoleNom(roleNom), HttpStatus.OK);
	}
	
	@RequestMapping(value="searchNative", method=RequestMethod.GET)
	public ResponseEntity<Iterable<User>> findByNativeNom(@RequestParam(required=true,name="nom") String nom){		
		return new ResponseEntity<Iterable<User>>(userDao.findByNative(nom), HttpStatus.OK);
	}
	
}
