package com.truyentranh.webtruyen.security.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.truyentranh.webtruyen.security.models.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query(value = "SELECT * FROM role WHERE id NOT IN (SELECT role_id FROM users_roles WHERE user_id = ?1)", 
	        nativeQuery = true)
	Collection<Role> getUserNotRoles(long userId);
}
