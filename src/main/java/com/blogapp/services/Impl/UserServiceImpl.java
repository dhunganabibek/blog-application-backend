package com.blogapp.services.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.dtos.UserDTO;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.respositories.UserRepo;
import com.blogapp.services.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		userRepo.save(user);
		userDTO.setId(user.getId());
		return userDTO;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		BeanUtils.copyProperties(userDTO, user, "id");
		userRepo.save(user);
		userDTO.setId(userId);
		return userDTO;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return  userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> users = userRepo.findAll();
		return users.stream().map(user -> {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			return userDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		userRepo.delete(user);
		
	}

}
