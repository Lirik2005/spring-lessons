package com.spring.database.repository;

import com.spring.database.entity.Role;
import com.spring.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    /**
     * Метод ниже способен вернуть нам первого пользователя с конца
     */

    Optional<User> findTopByOrderByIdDesc();

    /**
     * Метод ниже выведет трех пользователей с конца, которые будут отсортированы по указанной дате рождения
     */
    List<User> findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate birthDate);

    /**
     * Метод ниже выведет трех пользователей с конца. Также мы можем передать в метод параметр Sort, который позволит нам делать
     * динамическую сортировку
     * Аннотация @Lock позволяет нам устанавливать пессимистические или оптимистические блокировки на уровне строк
     * Аннотация @QueryHints используется для дополнительной оптимизации нашего запроса (изменить тайм-аут, использовать кэширование
     * запроса)
     */

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    /**
     * Метод ниже принимает в себя Pageable. Описание смотри в UserRepositoryTest. В возвращаемом типе мы можем вместо List использовать
     * Slice, который нам предоставляет Spring
     * Вместо Slice также можно использовать Page (наследуется от Slice). В нем есть методы getTotalElements и getTotalCount, которые
     * могут показать нам сколько всего есть страниц по заданному условию.
     * С помощью @Query мы также можем изменить дефолтное поведение counter.
     * <p>
     * Аннотация @EntityGraph("User.company") позволяет нам сделать join с таблицей Company (смотри сущность User)
     * Однако лучше использовать другой атрибут: attribute path, но тут будут некорректно работать Pageable
     */

//    @EntityGraph("User.company")
    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "select u from User u", countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);
}
