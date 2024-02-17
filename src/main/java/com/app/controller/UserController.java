package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.service.UserService;
import com.app.util.IsAuthorizedAdmin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RequestMapping("/user")
@RestController
@SecurityRequirement(name = "JWT-Token")
public class UserController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	@IsAuthorizedAdmin  //this end point only ADMIN User can access, there no permission for PUBLIC
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User addUser = userService.addUser(user);
		LOGGER.info("Successfully user is register in Database");
		return new ResponseEntity<User>(addUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers() {
		List<User> users = userService.getAllUsers();
		LOGGER.info("Successfully user is register in Database");
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<?> getUserByUserId(@PathVariable("userId") String userId) {
	return userService.getUserByUserId(userId)
		.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
		//LOGGER.info("Successfully user is register in Database");
		//return new ResponseEntity<User>(user, HttpStatus.CREATED);
		//return user.m
	}
	
	

}
