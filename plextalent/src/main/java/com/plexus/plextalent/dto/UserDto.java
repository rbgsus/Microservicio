package com.plexus.plextalent.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.plexus.plextalent.model.User;

import lombok.Data;

/**
 * This class represents a Data Transfer Object (DTO) for a list of users. It
 * contains a pageable object and a list of users retrieved from the database.
 */
@Data
public class UserDto {
	private Pageable pageable;
	private List<User> userList;

	/**
	 * Constructor that creates a new instance of UserDto with the provided pageable
	 * and user list.
	 *
	 * @param pageable The Pageable object containing the pagination parameters for
	 *                 the user list.
	 * @param userList The list of User objects retrieved from the database.
	 */
	public UserDto(Pageable pageable, List<User> userList) {
		this.pageable = pageable;
		this.userList = userList;
	}
}