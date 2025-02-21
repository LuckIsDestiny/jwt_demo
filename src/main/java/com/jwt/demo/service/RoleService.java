package com.jwt.demo.service;

import java.util.List;

import com.jwt.demo.model.RoleModel;

public interface RoleService {

	public RoleModel createRole(RoleModel roleModel);

	public List<RoleModel> getAllRoles();

	public RoleModel getRoleById(Long id);
	
	public void deleteRole(Long id);
}
