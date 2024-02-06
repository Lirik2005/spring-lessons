package com.spring.service;

import com.spring.database.repository.CompanyRepository;
import com.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Аннотация @Service (внутри помечена как @Component) это аналог аннотации @Component и используется над уровнем сервисов
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Здесь используем CrudRepository так как CompanyRepository у нас прокси.
     */
    private final CompanyRepository companyRepository;
}
