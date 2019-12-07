package com.ggfied.samplelogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggfied.samplelogin.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleName(String roleName);
}
