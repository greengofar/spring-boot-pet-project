package com.andev.integration.service;

import com.andev.dto.UserCreateEditDto;
import com.andev.dto.UserReadDto;
import com.andev.integration.IntegrationTestBase;
import com.andev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class UserServiceIT extends IntegrationTestBase {

    private final UserService userService;
    public static final Integer USER_1 = 1;

    @Test
    void findAll() {
        List<UserReadDto> actualResult = userService.findAll();
        assertThat(actualResult).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUserName()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "user@gmail.com",
                "test",
                "testLastName",
                "test@gmail.com",
                "test@gmail.com",
                "+37529485625877",
                new MockMultipartFile("test", new byte[0])
        );

        UserReadDto actualResult = userService.create(userDto);

        assertEquals(userDto.getUserName(), actualResult.getUserName());
        assertEquals(userDto.getFirstName(), actualResult.getFirstName());
        assertEquals(userDto.getLastName(), actualResult.getLastName());
        assertEquals(userDto.getEmail(), actualResult.getEmail());
        assertEquals(userDto.getPhone(), actualResult.getPhone());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "user@gmail.com",
                "test",
                "testLastName",
                "test@gmail.com",
                "test@gmail.com",
                "+37529485625877",
                new MockMultipartFile("test", new byte[0])
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUserName(), user.getUserName());
            assertEquals(userDto.getFirstName(), user.getFirstName());
            assertEquals(userDto.getLastName(), user.getLastName());
            assertEquals(userDto.getEmail(), user.getEmail());
            assertEquals(userDto.getPhone(), user.getPhone());
        });
    }

    @Test
    void delete() {
        assertTrue(userService.delete(USER_1));
        assertFalse(userService.delete(-5));
    }
}