package org.example;

import org.example.database.pool.ConnectionPool;
import org.example.database.repository.CompanyRepository;
import org.example.database.repository.UserRepository;
import org.example.ioc.Container;
import org.example.service.UserService;

public class ApplicationRunner {

    public static void main(String[] args) {

        Container container = new Container();

        /**
         * Код приведенный ниже это внедрение зависимостей. Но так писать нельзя, потому что надо создавать огромное количество сущностей
         */

        ConnectionPool pool = new ConnectionPool();
        UserRepository repositoryUser = new UserRepository(pool);
        CompanyRepository repositoryCompany = new CompanyRepository(pool);
        UserService serviceUser = new UserService(repositoryUser, repositoryCompany);

        /**
         * Код ниже показывает как выглядит инверсия управления. Мы теперь не управляем созданием объекта. Тут, чтобы получить userService
         * нам не надо создавать connectionPool, userRepository и companyRepository. В итоге мы даже не знаем про зависимости у userService
         */

        ConnectionPool connectionPool = container.get(ConnectionPool.class);
        UserRepository userRepository = container.get(UserRepository.class);
        CompanyRepository companyRepository = container.get(CompanyRepository.class);
        UserService userService = container.get(UserService.class);
    }
}