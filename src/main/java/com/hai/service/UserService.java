package com.hai.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hai.entity.User;
import com.hai.repository.UserRepository;

@Service
public class UserService {

	Timestamp currentTs = new Timestamp(System.currentTimeMillis());

	@Autowired
	private UserRepository userRepositry;

	public User findByUsernameAndPassword(String username, String password) {
		User user = userRepositry.findByUsernameAndPassword(username, password);
		return user;
	}

	public void save(User user) {
		userRepositry.save(user);
	}

}
