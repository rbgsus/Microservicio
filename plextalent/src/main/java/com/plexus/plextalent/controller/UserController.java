package com.plexus.plextalent.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plexus.plextalent.dto.UserDto;
import com.plexus.plextalent.model.User;
import com.plexus.plextalent.service.impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * This class is the user controller, that provides the end points of the user
 * api
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.version}")
@Tag(name = "user", description = "This endpoint manages information about Plex Talent users.")
public class UserController {
	private final UserServiceImpl userServiceImpl;

	/**
	 * This endpoint uses HTTP method POST and saves a user object to the database
	 *
	 * @param user This parameter is a user object to be saved to the database
	 */
	@PostMapping("/user")
	@Operation(summary = "Add a new user to the db", description = "Add a new user to the db by defining information in body")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public void save(@RequestBody User user) {
		userServiceImpl.save(user);
	}

	/**
	 * This endpoint uses HTTP method GET and retrieves a list of all user objects
	 * from the database
	 *
	 * @return This method returns a list of all user objects found in the database
	 */
	@GetMapping("/user")
	@Operation(summary = "Show all users from db", description = "Show all users from db")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public List<User> findAll() {
		return userServiceImpl.findAll();
	}

	/**
	 * This endpoint uses HTTP method GET and retrieves a user object from the
	 * database based on the given ID
	 *
	 * @param id This parameter is the ID of the user object to be retrieved from
	 *           the database
	 * @return This method returns a user object with the matching ID if found in
	 *         the database, otherwise returns null
	 */
	@GetMapping("/user/{id}")
	@Operation(summary = "Show a specific user", description = "Show a specific user introducing the id in path")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public User findByID(@Parameter(in = ParameterIn.PATH, name = "id", required = true) @PathVariable Long id) {
		return userServiceImpl.findById(id).orElse(null);
	}

	/**
	 * This endpoint uses HTTP method DELETE and deletes a user object from the
	 * database based on the given ID
	 *
	 * @param id This parameter is the ID of the user object to be deleted from the
	 *           database
	 */
	@DeleteMapping("/user/{id}")
	@Operation(summary = "Delete an user from db", description = "Delete an user from db introducing the id in path")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal error") })

	public void delete(@Parameter(in = ParameterIn.PATH, name = "id", required = true) @PathVariable Long id) {
		userServiceImpl.deleteById(id);
	}

	/**
	 * This endpoint uses HTTP method GET and retrieves a paginated list of users
	 *
	 * @param page This parameter is the page number to retrieve (required)
	 * @param size This parameter is the number of elements per page (required)
	 * @return This method returns a user list in a page
	 */

	@GetMapping("/users")
	@Operation(summary = "Show paginated user list", description = "Show paginated user list")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public UserDto listUsers(@Parameter(in = ParameterIn.QUERY, name = "page", required = true) @RequestParam int page,
			@Parameter(in = ParameterIn.QUERY, name = "size", required = true) @RequestParam int size) {
		return userServiceImpl.listUsers(page, size);
	}

}
