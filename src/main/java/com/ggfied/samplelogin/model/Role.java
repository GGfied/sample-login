package com.ggfied.samplelogin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "app_role", indexes = {
		@Index(name = "role_name_idx", columnList = "role_name", unique = true)
})
@NoArgsConstructor
@Data
public class Role {

	public static final String USER = "USER";
	public static final String ADMIN = "ADMIN";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "role_name", nullable = false, unique = true)
	@NonNull
	@NotBlank(message = "Role Name is required")
	private String roleName;

	@Column(name = "description", nullable = false)
	private String description;
	
//	@Column(name = "created_at", nullable = false)
//	@CreatedDate
//	private Date createdAt;
//
//	@Column(name = "updated_at", nullable = false)
//	@LastModifiedDate
//	private Date updatedAt;
//
//	@Column(name = "updated_by", nullable = false)
//	@LastModifiedBy
//	private String updatedBy;

}
