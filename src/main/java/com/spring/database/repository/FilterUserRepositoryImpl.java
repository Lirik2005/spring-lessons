package com.spring.database.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.spring.database.entity.QUser;
import com.spring.database.entity.Role;
import com.spring.database.entity.User;
import com.spring.database.querydsl.QPredicates;
import com.spring.dto.PersonalInfo;
import com.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.spring.database.entity.QUser.user;


/**
 * Класс реализует интерфейс FilterUserRepository. Он должен называться так же как интерфейс и иметь приставку Impl
 */
@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT 
            firstname,
            lastname,
            birth_date
            FROM users
            WHERE company_id =?
            AND role = ?
            """;

    private final EntityManager entityManager;

    /**
     * Необходим для работы с JDBC
     */

    private final JdbcTemplate jdbcTemplate;

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

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE,
                                  (rs, rowNum) -> new
                                          PersonalInfo(rs.getString("firstname"),
                                                       rs.getString("lastname"),
                                                       rs.getDate("birth_date").toLocalDate()
                                  ), companyId, role.name());
    }
}
