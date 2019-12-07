package com.ggfied.samplelogin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ggfied.samplelogin.model.User;
import com.ggfied.samplelogin.service.AuthenticationService;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("register")
	public ResponseEntity<Object> register(@Valid @ModelAttribute User user) {
		Optional<User> newUser = this.authenticationService.register(user);
		
		return newUser.isPresent() ?
				ResponseEntity.ok().body(newUser.get()) :
					ResponseEntity.badRequest().body(String.format("Username '%s' is taken.", user.getUsername()));
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
