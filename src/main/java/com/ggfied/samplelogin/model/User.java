package com.ggfied.samplelogin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "app_user", indexes = { @Index(name = "username_idx", columnList = "username", unique = true) })
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(exclude = "roles")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username", nullable = false, unique = true)
	@NonNull
	@NotBlank(message = "Username is required")
	private String username;

	@Column(name = "password", nullable = false)
	@NonNull
	@NotBlank(message = "Password is required")
	@JsonIgnore
	private String password;

	@Column(name = "name", nullable = false)
	@NonNull
	@NotBlank(message = "Name is required")
	private String name;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "language_id", referencedColumnName = "id")
	private Language language;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "app_user_role", joinColumns = @JoinColumn(name = "app_user_id"), inverseJoinColumns = @JoinColumn(name = "app_role_id"))
	private List<Role> roles = new ArrayList<>();
	
	public Object[] getRoleNames() {
		return this.roles.stream().map(v -> v.getRoleName()).toArray();
	}

}
