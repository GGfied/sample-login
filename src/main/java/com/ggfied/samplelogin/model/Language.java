package com.ggfied.samplelogin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "language")
@NoArgsConstructor
@Data
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "code", nullable = false, unique = true)
	@NonNull
	@NotBlank(message = "Code is required")
	private String code;

	@Column(name = "name", nullable = false)
	@NonNull
	@NotBlank(message = "Name is required")
	@JsonIgnore
	private String name;

}
