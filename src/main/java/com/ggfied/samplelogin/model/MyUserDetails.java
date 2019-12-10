package com.ggfied.samplelogin.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NonNull;

public class MyUserDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	@NonNull
	@Getter
	@JsonIgnore
	private transient User user;

	public MyUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), authorities);

		this.user = user;
	}

}
