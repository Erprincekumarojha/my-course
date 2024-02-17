package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.User;

public interface UserService {

	User addUser(User user);

	List<User> getAllUsers();

	Optional<User> getUserByUserId(String userId);
	

}
