package com.spring.database.repository;

import com.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс Repository позволяет отказаться от своих самописных репозиториев. Это автоконфигурация, которая создает repository-слой.
 * В Spring Data JPA не принято использовать конкретные реализации. Поэтому CompanyRepository должен быть интерфейсом.
 * <p>
 * У интерфейса Repository есть ряд наследников, один из которых это CrudRepository. Если мы используем его, то нам уже не надо самим
 * писать такие методы как findById или delete.
 * Также есть еще JpaRepository, который еще больше расширяет CrudRepository.
 */

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    /**
     * Этот метод фактически поставляется нам из коробки. ЕГо просто надо написать как ниже и все заведется.
     * Аннотация @Param необходима, чтобы правильно сматчить поле класса со значением в запросе у аннотации @NamedQuery в классе Company
     * <p>
     * Вместо @NamedQuery над классом Company, мы можем использовать @Query прямо над методом, чтобы прикрутить новый запрос и заменить
     * им уже существующий запрос по умолчанию.
     */
    @Query("select c from Company c join fetch c.locales cl where c.name = :name2")
    Optional<Company> findByName(@Param("name2") String name);

    /**
     * Метод ниже также поставляется нам из коробки и позволит нам вернуть List компаний по части их названия. ЕГо просто надо написать как
     * ниже и все заведется.
     * Однако в таком случае, название метода получается довольно большим и громоздким, что не есть хорошо.
     */
    List<Company> findALlByNameContainingIgnoreCase(String fragment);
}
