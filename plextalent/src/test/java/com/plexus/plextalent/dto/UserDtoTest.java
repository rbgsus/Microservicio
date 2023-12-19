package com.plexus.plextalent.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import com.plexus.plextalent.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class UserDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Create a test Pageable object
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        // Create a test list of User objects & add them to list
        List<User> userList = new ArrayList<>();
        User user1 = new User (1L,"example","examplels","example");
        User user2 = new User (2L,"example2","examplels2","example2");
        userList.add(user1);
        userList.add(user2);

        // Create an instance of UserDto using the constructor
        UserDto userDto = new UserDto(pageable, userList);

        // Verify that the objectDto is created
        assertEquals(pageable, userDto.getPageable());
        assertEquals(userList, userDto.getUserList());
    }
}