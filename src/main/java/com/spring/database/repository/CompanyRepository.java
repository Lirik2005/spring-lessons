package com.spring.database.repository;


import com.spring.database.entity.Company;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * Интерфейс Repository позволяет отказаться от своих самописных репозиториев. Это автоконфигурация, которая создает repository-слой.
 * В Spring Data JPA не принято использовать конкретные реализации. Поэтому CompanyRepository должен быть интерфейсом.
 *
 * У интерфейса Repository есть ряд наследников, один из которых это CrudRepository. Если мы используем его, то нам уже не надо самим
 * писать такие методы как findById или delete.
 * Также есть еще JpaRepository, который еще больше расширяет CrudRepository.
 */

public interface CompanyRepository extends Repository<Company, Integer> {

    Optional<Company> findById(Integer id);

    void delete(Company entity);
}
