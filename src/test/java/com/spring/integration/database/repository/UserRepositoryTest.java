package com.spring.integration.database.repository;

import com.spring.database.entity.Role;
import com.spring.database.entity.User;
import com.spring.database.repository.UserRepository;
import com.spring.dto.PersonalInfo2;
import com.spring.dto.UserFilter;
import com.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkAuditing() {
        User ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImplementation() {
        UserFilter filter = new UserFilter(null, "%ov%", LocalDate.now());
        List<User> users = userRepository.findAllByFilter(filter);
        System.out.println();
    }

    @Test
    void checkProjections() {
        List<PersonalInfo2> users = userRepository.findAllByCompanyId(1);
        assertEquals(2, users.size());
    }

    @Test
    void checkPageable() {
        /**
         * Запись ниже позволяет нам сделать пагинацию с сортировкой по id. Здесь мы получим юзеров начиная со второй страницы (нумерация
         страниц начинается с нуля и количеством пользователей на странице 2 (плюс сортированных по id)
         */

        PageRequest pageable = PageRequest.of(0, 2, Sort.by("id"));
        Page<User> slice = userRepository.findAllBy(pageable);
        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));

        }
    }

    @Test
    void checkSort() {
        /**
         * Запись ниже позволяет нам динамически осуществлять сортировку в зависимости от класса и отказаться от хардкода.
         */

        Sort.TypedSort<User> sortBy = Sort.sort(User.class);
        Sort sort = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));

        List<User> sortByName = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        assertEquals(3, sortByName.size());
    }

    @Test
    void checkFirstTop() {
        /**
         * Сортировку можно делать сложной как в примере ниже
         */

        List<User> sortByName = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), Sort.by("firstName").and(Sort.by("lastName")));
        assertEquals(3, sortByName.size());

        List<User> sortById = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), Sort.by("id").descending());
        assertEquals(3, sortById.size());

        List<User> sortByBirthDate = userRepository.findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate.now());
        assertEquals(3, sortByBirthDate.size());

        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

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