package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.entity.User;
import com.app.service.UserService;

import jakarta.annotation.PostConstruct;

@Component
public class AddDefaultAdminUser {
	
	@Autowired
	private UserService userService;
	
	//@PostConstruct
	public void addDefaultUser() {
	   User user = new User();
	   user.setId(0);
	   user.setUserId("admin");
	   user.setName("prince kumar");
	   user.setPassword("admin");
	   user.setRoles("ADMIN");
	   
	   userService.addUser(user);
	}

}
