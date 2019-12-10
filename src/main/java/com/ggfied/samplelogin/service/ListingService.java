package com.ggfied.samplelogin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggfied.samplelogin.model.Role;
import com.ggfied.samplelogin.model.User;
import com.ggfied.samplelogin.repository.UserRepository;

@Service
public class ListingService {

	@Autowired
	private UserRepository userRepository;

	public List<User> listUsers() {
		return this.userRepository.findAllByRoles_RoleName(Role.USER);
	}

}
