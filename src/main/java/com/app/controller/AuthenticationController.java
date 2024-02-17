package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.JwtTokenService;
import com.app.util.AuthRequest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "JWT-Token")
public class AuthenticationController {

	@Autowired(required = true)
	private JwtTokenService jwtTokenService;

	@Autowired(required = true)
	private AuthenticationManager authenticationManager;

	@PostMapping("/authenticate")
	public String authenticateForToken(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserId(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) {
			return jwtTokenService.generateToken(authRequest.getUserId());
		} else {
			throw new UsernameNotFoundException("Invalid userId and Password ?");
		}
	}

}
