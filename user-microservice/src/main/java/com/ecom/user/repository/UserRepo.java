package com.ecom.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecom.user.entity.User;

public interface UserRepo extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
}
