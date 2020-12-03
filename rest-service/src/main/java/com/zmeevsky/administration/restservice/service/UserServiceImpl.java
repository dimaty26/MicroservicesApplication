package com.zmeevsky.administration.restservice.service;

import com.zmeevsky.administration.restservice.entity.User;
import com.zmeevsky.administration.restservice.exception.UserAlreadyExistsException;
import com.zmeevsky.administration.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setbCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User theUser) {

		String email = theUser.getEmail();

		if (userRepository.existsByUsername(email)) {
			throw new UserAlreadyExistsException("User with email " + email + " already exists.");
		}
		theUser.setPassword(bCryptPasswordEncoder.encode(theUser.getPassword()));
		userRepository.save(theUser);
	}

	@Override
	@Transactional
	public void updateUser(User user) {

		User userFromDB = getUser(user.getId());

		if (!user.getPassword().equals(userFromDB.getPassword())) {
			if (!bCryptPasswordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			}
		}

		userRepository.save(user);
	}

	@Override
	@Transactional
	public User getUser(int theId) {
		return userRepository.getOne(theId);
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		return userRepository.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No user with username: " + username + " found."));
	}

	@Override
	@Transactional
	public void deleteUser(int theId) {
		userRepository.deleteById(theId);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userRepository.delete(user);
	}
}





