package com.plexus.plextalent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.plexus.plextalent.dto.UserDto;
import com.plexus.plextalent.model.User;
import com.plexus.plextalent.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserController userController;


    @Test
    void CreateUser(){
        User user = new User(1L,"example","examplels","passExample");
        userController.save(user);
        Mockito.verify(userServiceImpl).save(user);
    }

    @Test
    void findAllWithData(){
        List<User> simulatedCollection = new ArrayList<>();
        simulatedCollection.add(new User (1L,"example","examplels","example"));
        simulatedCollection.add(new User (2L,"demonstration","demostrationls","demonstration"));
        simulatedCollection.add(new User (3L,"sample","samplels","sample"));

        List<User> expected = new ArrayList<>();
        expected.add(new User (1L,"example","examplels","example"));
        expected.add(new User (2L,"demonstration","demostrationls","demonstration"));
        expected.add(new User (3L,"sample","samplels","sample"));

        Mockito.when(userServiceImpl.findAll()).thenReturn(simulatedCollection);

        final List<User> result = userServiceImpl.findAll();
        assertEquals(expected,result);
    }

    @Test
    void findAllWithoutData(){
        List<User> simulatedCollection = new ArrayList<>();
        List<User> expectedCollection = new ArrayList<>();

        Mockito.when(userServiceImpl.findAll()).thenReturn(simulatedCollection);

        final List<User> result = userController.findAll();
        assertEquals(expectedCollection,result);
    }

    @Test
    void deleteNonExistenceIdShouldWork(){ //deleteById is a void function, nothing to compare. This test is just to be sure the function works not deleting with non existence id's
        userController.delete(-1L);
        verify(userServiceImpl).deleteById(-1L);
    }

    @Test
        void findNonExistenceId(){
        Optional<User> simulatedObject = Optional.empty();
        Mockito.when(userServiceImpl.findById(-1L)).thenReturn(simulatedObject);
        final User result = userController.findByID(-1L);

        assertNull(result);

    }

    @Test
    void findExistenceId(){
        Optional<User> simulatedObject = Optional.of(new User (1L,"admin","adminls","admin"));
        Optional<User> expected = Optional.of(new User(1L,"admin","adminls","admin"));
        Mockito.when(userServiceImpl.findById(1L)).thenReturn(simulatedObject);
        final User result = userController.findByID(1L);
        assertEquals(expected.get(),result);
    }

    @Test
    void testListUsers() {
        int page=0;
        int size=3;
        List<User> simulatedCollection = new ArrayList<>();
        simulatedCollection.add(new User(1L, "example", "examplels", "example"));
        simulatedCollection.add(new User(2L, "demonstration", "demostrationls", "demonstration"));
        simulatedCollection.add(new User(3L, "sample", "samplels", "sample"));

        Pageable pageable = PageRequest.of(page, size);
        UserDto expectedUserDto = new UserDto(pageable, simulatedCollection);

        Mockito.when(userServiceImpl.listUsers(page, size)).thenReturn(expectedUserDto);

        UserDto actualUserDto = userController.listUsers(page, size);
        assertEquals(expectedUserDto, actualUserDto);

    }


}