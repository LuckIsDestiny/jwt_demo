package com.jwt.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.demo.model.RoleModel;
import com.jwt.demo.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@PostMapping("/roles")
	public RoleModel createRole(@RequestBody RoleModel roleModel) {
		return roleService.createRole(roleModel);
	}
	
	@GetMapping("/roles")
	public List<RoleModel> getAllRoles() {
		return roleService.getAllRoles();
	}
	
	@DeleteMapping("/roles/{id}")
	public void deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
	}
	
}
