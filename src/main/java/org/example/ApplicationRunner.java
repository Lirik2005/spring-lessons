package org.example;

import org.example.config.ApplicationConfiguration;
import org.example.database.pool.ConnectionPool;
import org.example.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        /**
         * Оборачиваем в try with resources, чтобы application context автоматически закрывался
         */

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {

            /**
             * Записи ниже нужны для активации наших профайлов. Вызов context.refresh() это обязательно!!!
             * Лучше всего вызывать profile из application.properties!!!
             */
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();

            /**
             * Запись context.getBean(ConnectionPool.class) мы не можем использовать, если у нас есть несколько бинов одного и того же
             * класса.
             * Поэтому для обращения к конкретному бину используем запись ниже, а именно через id бина
             */
            ConnectionPool connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);

            var companyService = context.getBean(CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}