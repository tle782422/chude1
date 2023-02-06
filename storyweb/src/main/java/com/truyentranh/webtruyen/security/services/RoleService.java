package com.truyentranh.webtruyen.security.services;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.security.models.Role;
import com.truyentranh.webtruyen.security.models.User;
import com.truyentranh.webtruyen.security.repositories.RoleRepository;
import com.truyentranh.webtruyen.security.repositories.UserRepository;




@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	//Get All Roles
	public List<Role> findAll(){
		return roleRepository.findAll();
	}	
	
	//Get Role By Id
	public Role findById(long id) {
		return roleRepository.findById(id).orElse(null);
	}	
	
	//Delete Role
	public void delete(long id) {
		roleRepository.deleteById(id);
	}
	
	//Update Role
	public void save( Role Role) {
		roleRepository.save(Role);
	}
	
	public void assignUserRole(long userId, long roleId){
	    User user  = userRepository.findById(userId).orElse(null);
	    Role role = roleRepository.findById(roleId).orElse(null);
	    Collection<Role> userRoles = user.getRoles();
	   userRoles.add(role);
	   user.setRoles(userRoles);
	   userRepository.save(user);
	}
	
	public void unassignUserRole(long userId, long roleId){
	    User user  = userRepository.findById(userId).orElse(null);
	    user.getRoles().removeIf(x -> x.getId()==roleId);
	    userRepository.save(user);
	}
	
	public Collection<Role> getUserRoles(User user){
	    return user.getRoles();
	}
	
	public Collection<Role> getUserNotRoles(User user){
		   return roleRepository.getUserNotRoles(user.getId());
	}
}
