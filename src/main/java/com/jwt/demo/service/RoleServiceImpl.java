package com.jwt.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.demo.entity.RoleEntity;
import com.jwt.demo.model.RoleModel;
import com.jwt.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleModel createRole(RoleModel roleModel) {
		
		RoleEntity roleEntity = new RoleEntity();
		BeanUtils.copyProperties(roleModel, roleEntity);
		
		RoleEntity roleEntity1 = roleRepository.save(roleEntity);

		BeanUtils.copyProperties(roleEntity1, roleModel);
		return roleModel;
	}

	@Override
	public List<RoleModel> getAllRoles() {
		List<RoleEntity> roleEntities = roleRepository.findAll();
		List<RoleModel> roleModels = new ArrayList<>();
		for(RoleEntity re : roleEntities) {
			RoleModel roleModel = new RoleModel();
			BeanUtils.copyProperties(re, roleModel);
			roleModels.add(roleModel);
		}
		return roleModels;
	}

	@Override
	public RoleModel getRoleById(Long id) {
		RoleEntity roleEntity = roleRepository.findById(id).get();
		RoleModel roleModel = new RoleModel();
		BeanUtils.copyProperties(roleEntity, roleModel);
		return roleModel;
	}

	@Override
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);;
		
	}

}
