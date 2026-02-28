package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.User;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.repo.UserRepo;
import com.ecom.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userRepo;

	public List<User> fetchAllUsers() {
		List<User> users = userRepo.findAll();
		return users;
	}

	@Override
	public User addUser(User user) {
		if (userRepo.existsByUsername(user.getUsername())) {
			return user;
		}
		User newUser = userRepo.save(user);
		return newUser;
	}

	@Override
	public User fetchUserById(Long id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
		return user;
	}

	@Override
	public User updateUser(User user, Long id) {

		User update = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getUsername()));

		update.setFirstname(user.getFirstname());
		update.setLastname(user.getLastname());

		userRepo.saveAndFlush(update);
		return update;
	}
}
