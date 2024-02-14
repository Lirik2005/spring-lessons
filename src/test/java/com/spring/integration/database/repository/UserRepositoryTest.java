package com.spring.integration.database.repository;

import com.spring.database.entity.Role;
import com.spring.database.entity.User;
import com.spring.database.repository.UserRepository;
import com.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkUpdate() {
        User ivan = userRepository.getById(1L);
        assertEquals(Role.ADMIN, ivan.getRole());

        int resultCount = userRepository.updateRoles(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User theSameIvan = userRepository.getById(1L);
        assertEquals(Role.USER, theSameIvan.getRole());
    }

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertEquals(3, users.size());
        System.out.println(users);
    }
}