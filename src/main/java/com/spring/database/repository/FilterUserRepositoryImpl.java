package com.spring.database.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.spring.database.entity.QUser;
import com.spring.database.entity.User;
import com.spring.database.querydsl.QPredicates;
import com.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.spring.database.entity.QUser.user;


/**
 * Класс реализует интерфейс FilterUserRepository. Он должен называться так же как интерфейс и иметь приставку Impl
 */
@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    /**
     * Метод выбирает нам юзеров, исходя из фильтра.
     */
    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        Predicate predicate = QPredicates.builder()
                                         .add(filter.firstname(), user.firstname::containsIgnoreCase)
                                         .add(filter.lastname(), user.lastname::containsIgnoreCase)
                                         .add(filter.birthDate(), user.birthDate::before)
                                         .build();
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();

    }
}
