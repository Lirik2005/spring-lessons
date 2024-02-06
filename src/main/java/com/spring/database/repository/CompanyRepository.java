package com.spring.database.repository;


import com.spring.database.entity.Company;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * Интерфейс Repository позволяет отказаться от своих самописных репозиториев. Это автоконфигурация, которая создает repository-слой.
 * В Spring Data JPA не принято использовать конкретные реализации. Поэтому CompanyRepository должен быть интерфейсом.
 */

public interface CompanyRepository extends Repository<Company, Integer> {

    Optional<Company> findById(Integer id);

    void delete(Company entity);
}
