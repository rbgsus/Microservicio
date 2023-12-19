package com.plexus.plextalent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.plexus.plextalent.dto.UserDto;
import com.plexus.plextalent.model.User;
import com.plexus.plextalent.repository.UserRepository;
import com.plexus.plextalent.service.UserService;

/**
 * This class implements UserService interface
 */
@Service

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	/**
	 * Constructor of UserServiceImpl
	 *
	 * @param userRepository This param is an object of UserRepository
	 */
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * This method creates a new user record in db
	 *
	 * @param user This param is an object of user
	 */
	public void save(User user) {
		userRepository.save(user);
	}

	/**
	 * This method returns a list of all db user records
	 *
	 * @return method userRepository.findAll()
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * This method returns a user record looking for it by id
	 *
	 * @param id This param is the id of the db user
	 * @return method userRepository.findById()
	 */
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	/**
	 * This method deletes a user record looking for it by id
	 *
	 * @param id This param is the id of the db user
	 */

	public void deleteById(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}
	}

	/**
	 * This method returns a page of users based on the provided pageable parameters
	 *
	 * @param page This parameter is the page number to retrieve
	 * @param size This parameter is the number of elements per page
	 * @return This method returns a user list in a page
	 */

	public UserDto listUsers(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<User> userPage = userRepository.findAll(pageable);
		List<User> userList = userPage.getContent();
		UserDto userDTO = new UserDto(pageable, userList);
		return userDTO;
	}

}
