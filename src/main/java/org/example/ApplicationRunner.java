package org.example;

import org.example.database.pool.ConnectionPool;
import org.example.database.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        /**
         * Оборачиваем в try with resources, чтобы application context автоматически закрывался
         */

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {

            /**
             * Запись context.getBean(ConnectionPool.class) мы не можем использовать, если у нас есть несколько бинов одного и того же
             * класса.
             * Поэтому для обращения к конкретному бину используем запись ниже, а именно через id бина
             */
            ConnectionPool connectionPool = context.getBean("first", ConnectionPool.class);
            System.out.println(connectionPool);

            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}