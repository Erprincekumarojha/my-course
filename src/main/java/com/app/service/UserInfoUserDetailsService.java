package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.util.UserInfoDetailsUtil;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUserId(userId);
		return user.map(UserInfoDetailsUtil::new)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found Exception" + userId));
	}

}
