package com.ggfied.samplelogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggfied.samplelogin.model.User;
import com.ggfied.samplelogin.service.ListingService;

@RestController
@RequestMapping("/api/")
public class ListingController {

	@Autowired
	private ListingService listingService;

	@GetMapping("users")
	public ResponseEntity<Object> listUsers() {
		List<User> users = this.listingService.listUsers();

		return ResponseEntity.ok().body(users);
	}

}
