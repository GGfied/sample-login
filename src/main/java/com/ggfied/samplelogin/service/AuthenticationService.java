package com.ggfied.samplelogin.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ggfied.samplelogin.model.Role;
import com.ggfied.samplelogin.model.User;
import com.ggfied.samplelogin.repository.RoleRepository;
import com.ggfied.samplelogin.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final Optional<User> errorResult = Optional.ofNullable(null);

	@Transactional
	public Optional<User> register(User user) {
		Optional<User> resultUser = Optional.ofNullable(this.userRepository.findByUsername(user.getUsername()));

		if (resultUser.isPresent()) {
			return this.errorResult;
		}

		Role role = this.roleRepository.findByRoleName(Role.USER);

		if (role == null) {
			log.error("'{}' role not found", Role.USER);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			return this.errorResult;
		}

		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		resultUser = Optional.ofNullable(this.userRepository.save(user));

		if (!resultUser.isPresent()) {
			log.error("'{}' role not found", Role.USER);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			return this.errorResult;
		}

		resultUser.get().getRoles().add(role);

		return resultUser;
	}

}
