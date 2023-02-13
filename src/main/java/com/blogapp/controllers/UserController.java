package com.blogapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dtos.UserDTO;
import com.blogapp.respositories.UserRepo;
import com.blogapp.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//POST: create User
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
		UserDTO createUserDTO = userService.createUser(userDTO);
		return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
		
	}
	
	
	//PUT: Update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("userId") Integer userId){
		UserDTO updatedUser = userService.updateUser(userDTO, userId);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	
	//DELETE: delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("userId") Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity(Map.of("message", "User Deleted sucessfully"), HttpStatus.OK);
	}
	
	
	
	//Get- get all user
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	
	//get specific users
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> sayHello(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}
}
