package com.ggfied.samplelogin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ggfied.samplelogin.model.Role;
import com.ggfied.samplelogin.model.User;
import com.ggfied.samplelogin.service.AuthenticationService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/authentication/register")
	public ResponseEntity<?> register(@Valid @RequestBody User user) {
		Optional<User> newUser = this.authenticationService.register(user);

		return newUser.isPresent() ? ResponseEntity.ok().body(newUser.get())
				: ResponseEntity.badRequest().body(String.format("Username '%s' is taken.", user.getUsername()));
	}

	@GetMapping("/api/test-token")
	public ResponseEntity<?> testToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(v -> v.getAuthority().equalsIgnoreCase(Role.ADMIN));
		
		return ResponseEntity.ok().body("{\"isAdmin\":"+isAdmin+"}");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();

			errors.put(fieldName, errorMessage);
		});

		return errors;
	}

}
