package com.spring.service;

import com.spring.database.entity.Company;
import com.spring.database.repository.CrudRepository;
import com.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Аннотация @Service (внутри помечена как @Component) это аналог аннотации @Component и используется над уровнем сервисов
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Здесь используем CrudRepository так как CompanyRepository у нас прокси.
     */
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository, CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

}
