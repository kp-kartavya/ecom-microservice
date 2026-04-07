package com.ecom.user.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.user.dto.UserDto;
import com.ecom.user.dto.UserRole;
import com.ecom.user.entity.User;
import com.ecom.user.repository.UserRepo;
import com.ecom.user.service.UserService;
import com.ecom.user.exception.ResourceNotFoundException;
import com.ecom.user.exception.UsernameAlreadyExistsException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	ModelMapper modelMapper;

	public List<UserDto> fetchAllUsers() {
		List<User> users = userRepo.findAll();
		return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
	}

	@Override
	public UserDto addUser(UserDto user) {
		User users = modelMapper.map(user, User.class);
		if (userRepo.existsByUsername(users.getUsername())) {
			throw new UsernameAlreadyExistsException("User", "username", users.getUsername());
		}
		if (users.getRole() == null) {
			users.setRole(UserRole.CUSTOMER);
		}
		User newUser = userRepo.save(users);
		return modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public User fetchUserById(String id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return user;
	}

	@Override
	public UserDto updateUser(UserDto user, Long id) {
		User existingUser = userRepo.findById(String.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
		User update = modelMapper.map(existingUser, User.class);
		User updatedUser = modelMapper.map(user, User.class);
		update.setFirstname(updatedUser.getFirstname());
		update.setLastname(updatedUser.getLastname());
		update.setRole(updatedUser.getRole());
		if (updatedUser.getAddress() != null) {
			update.setAddress(updatedUser.getAddress());
		}
		update.setEmail(updatedUser.getEmail());
		update.setPhone(updatedUser.getPhone());
		log.info("Updating user with id: {}", update.getFirstname());
		userRepo.save(update);
		return modelMapper.map(update, UserDto.class);
	}
}
