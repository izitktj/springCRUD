package com.rinhaback.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rinhaback.api.domain.User.User;
import com.rinhaback.api.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
	private final UserService service;
	
	@GetMapping
	public List<User> getAllUsers() {
		return service.findAll();
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
 			return ResponseEntity.ok(service.save(user));
		}
		catch(Error e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable UUID id) {
	    try {
	        Optional<User> userToUpdate = service.findByID(id);

	        if (userToUpdate.isPresent()) {
	            User _userToUpdate = userToUpdate.get();

	            _userToUpdate.setName((user.getName() != null) ? user.getName() : _userToUpdate.getName());
	            _userToUpdate.setEmail((user.getEmail() != null) ? user.getEmail() : _userToUpdate.getEmail());
	            _userToUpdate.setPassword((user.getPassword() != null) ? user.getPassword() : _userToUpdate.getPassword());

	            return ResponseEntity.ok(service.save(_userToUpdate));
	        }

	        throw new RuntimeException("User not found");
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().build();
	    }
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable UUID id) {
		try{
			Optional<User> userToDelete = service.findByID(id);
			
			if(userToDelete.isPresent()) {
				service.deleteById(userToDelete.get().getId());
				return ResponseEntity.ok(userToDelete.get());
			}

			throw new RuntimeException();
		}
		catch(Error e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
