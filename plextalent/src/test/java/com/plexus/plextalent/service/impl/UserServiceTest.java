package com.plexus.plextalent.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.plexus.plextalent.dto.UserDto;
import com.plexus.plextalent.model.User;
import com.plexus.plextalent.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	void findNonExistenceId() {
		Optional<User> simulatedObject = Optional.empty();
		Mockito.when(userRepository.findById(-1L)).thenReturn(simulatedObject);
		final Optional<User> result = userServiceImpl.findById(-1L);
		assertNull(result.orElse(null));
	}

	@Test
	void findExistenceId() {
		Optional<User> simulatedObject = Optional.of(new User(1L, "admin", "adminls", "admin"));
		Optional<User> expected = Optional.of(new User(1L, "admin", "adminls", "admin"));
		Mockito.when(userRepository.findById(1L)).thenReturn(simulatedObject);
		final Optional<User> result = userServiceImpl.findById(1L);
		assertEquals(expected, result);
	}

	@Test
	void findAllWithData() {
		List<User> simulatedCollection = new ArrayList<>();
		simulatedCollection.add(new User(1L, "example", "examplels", "example"));
		simulatedCollection.add(new User(2L, "demonstration", "demostrationls", "demonstration"));
		simulatedCollection.add(new User(3L, "sample", "samplels", "sample"));

		List<User> expected = new ArrayList<>();
		expected.add(new User(1L, "example", "examplels", "example"));
		expected.add(new User(2L, "demonstration", "demostrationls", "demonstration"));
		expected.add(new User(3L, "sample", "samplels", "sample"));

		Mockito.when(userRepository.findAll()).thenReturn(simulatedCollection);

		final List<User> result = userServiceImpl.findAll();
		assertEquals(expected, result);
	}

	@Test
	void findAllWithoutData() {
		List<User> simulatedCollection = new ArrayList<>();
		List<User> expectedCollection = new ArrayList<>();

		Mockito.when(userRepository.findAll()).thenReturn(simulatedCollection);

		final List<User> result = userServiceImpl.findAll();
		assertEquals(expectedCollection, result);
	}

	@Test
    void deleteNonExistenceIdShouldWork() {
        when(userRepository.existsById(-1L)).thenReturn(false);
        userServiceImpl.deleteById(-1L);
        verify(userRepository, times(0)).deleteById(-1L);
    }

	@Test
	void createUser() {
		User example = new User(1L, "example", "exmaplels", "example");
		userServiceImpl.save(example);
		verify(userRepository).save(example);
	}

	@Test
	void testListUsers() {

		List<User> simulatedCollection = new ArrayList<>();
		simulatedCollection.add(new User(1L, "example", "examplels", "example"));
		simulatedCollection.add(new User(2L, "demonstration", "demostrationls", "demonstration"));
		simulatedCollection.add(new User(3L, "sample", "samplels", "sample"));

		Page<User> userPage = new PageImpl<>(simulatedCollection);

		Mockito.when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(userPage);
		UserDto userDto = userServiceImpl.listUsers(0, 3);

		Mockito.verify(userRepository, Mockito.times(1)).findAll(Mockito.eq(PageRequest.of(0, 3)));
		assertNotNull(userDto);
		assertEquals(simulatedCollection, userDto.getUserList());
		assertEquals(0, userDto.getPageable().getPageNumber());
		assertEquals(3, userDto.getPageable().getPageSize());
	}

}