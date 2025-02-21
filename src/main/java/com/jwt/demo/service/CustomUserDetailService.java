package com.jwt.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.demo.entity.UserEntity;
import com.jwt.demo.model.UserModel;
import com.jwt.demo.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserModel register(UserModel userModel) {
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userModel, userEntity);
		
		userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
		userEntity = userRepository.save(userEntity);
		BeanUtils.copyProperties(userEntity, userModel);
		
		return userModel;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("Username do not exist!!!");
		}

		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userEntity, userModel);

		return userModel;
	}

}
