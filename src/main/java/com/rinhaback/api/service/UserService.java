package com.rinhaback.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rinhaback.api.domain.User.User;
import com.rinhaback.api.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	private final UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public Optional<User> findByID(UUID id) {
		return repository.findById(id);
	}
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}
}
