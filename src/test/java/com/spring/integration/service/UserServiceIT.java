package com.spring.integration.service;

import com.spring.database.entity.Role;
import com.spring.dto.UserCreateEditDto;
import com.spring.dto.UserReadDto;
import com.spring.integration.IntegrationTestBase;
import com.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Так как сейчас у нас один контекст для всех интеграционных тестов, то можно использовать аннотацию @DirtiesContext и предавать туда
 * classMode (у classMode есть различные варианты). Это значит, что после каждого теста контекст будет помечен как dirty и его нельзя
 * переиспользовать в других тестах.
 * Эту аннотацию можно ставить и над конкретным тестом, передавая уже methodMode!
 * <p>
 * Но такой функционал рекомендуется использовать, только если в тестах ВРУЧНУЮ портится контекст!!!
 */
@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIT extends IntegrationTestBase {

    private static final Long IVAN_ID = 1L;

    private static final Integer COMPANY_ID = 1;

    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();

        assertEquals(5, result.size());
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(IVAN_ID);

        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_ID
        );
        UserReadDto actualResult = userService.create(userDto);

        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertSame(userDto.getRole(), actualResult.getRole());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_ID
        );
        Optional<UserReadDto> actualResult = userService.update(IVAN_ID, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertSame(userDto.getRole(), user.getRole());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
        });
    }

    @Test
    void delete() {
        boolean result = userService.delete(IVAN_ID);

        assertTrue(result);
        assertFalse(userService.delete(-12L));
    }
}
