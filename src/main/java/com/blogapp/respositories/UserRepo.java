package com.blogapp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
