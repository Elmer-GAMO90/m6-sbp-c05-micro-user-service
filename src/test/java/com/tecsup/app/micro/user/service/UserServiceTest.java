package com.tecsup.app.micro.user.service;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.mapper.UserMapper;
import com.tecsup.app.micro.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Test
    void getUserById() {
        Long ID = 100L;
        String NAME = "Jaime";
        String EMAIL = "jaime@demo.com";

        // Initial Condition
        User existingUser = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .build(); // new User(ID, NAME, EMAIL);

        // Mocking the repository behavior
        when(userRepository.findById(100L)).thenReturn(Optional.of(userMapper.toEntity(existingUser)));

        // Execute the service method
        User realUser = userService.getUserById(100L);

        // Validate the results
        assertNotNull(realUser);

        // hope values, real values
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(EMAIL, realUser.getEmail());
    }

    @Test
    void getAllUsers() {
    }
}