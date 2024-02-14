package com.spring.database.repository;

import com.spring.database.entity.Role;
import com.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * В этом запросе мы используем HQL
     */
    @Query("select u from User u where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    /**
     * Запрос ниже использует нативный SQL!!!
     */

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    /**
     * Аннотация @Query по умолчанию работает только для SELECT.
     * Чтобы выполнять DML-операции, также надо поставить аннотацию @Modifying. Параметр clearAutomatically = true позволяет очистить
     * persistence context, чтобы тест отработал корректно!!!
     */

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRoles(Role role, Long... ids);
}
