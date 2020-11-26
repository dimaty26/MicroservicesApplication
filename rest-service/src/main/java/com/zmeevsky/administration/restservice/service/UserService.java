package com.zmeevsky.administration.restservice.service;


import com.zmeevsky.administration.restservice.entity.User;

import java.util.List;

public interface UserService {

	List<User> getUsers();

	void saveUser(User theUser);

	void updateUser(User user);

	User getUser(int theId);

	void deleteUser(int theId);

	void delete(User user);

	User findByUsername(String username);
}
