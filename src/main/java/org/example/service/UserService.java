package org.example.service;

import org.example.database.entity.Company;
import org.example.database.repository.CompanyRepository;
import org.example.database.repository.CrudRepository;
import org.example.database.repository.UserRepository;
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
