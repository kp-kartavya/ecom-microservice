package com.ecom.user.service;

import java.util.List;

import com.ecom.user.dto.UserDto;
import com.ecom.user.entity.User;

public interface UserService {
	public List<UserDto> fetchAllUsers();

	public UserDto addUser(UserDto user);

	public User fetchUserById(String id);

	public UserDto updateUser(UserDto user, Long id);
}
