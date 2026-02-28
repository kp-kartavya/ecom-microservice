package com.ecom.service;

import java.util.List;

import com.ecom.entity.User;

public interface UserService {
	public List<User> fetchAllUsers();

	public User addUser(User user);

	public User fetchUserById(Long id);

	public User updateUser(User user, Long id);
}
