package com.spring.integration;

import com.spring.integration.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@IT
@Sql({
        "classpath:sql/data.sql"
})
public abstract class IntegrationTestBase {

    /**
     * Требуется, чтобы поднять контейнер с тестовой базой данных и в @BeforeAll мы поднимаем тест-контейнер (при этом всего один раз на
     * все наши тестовые классы)
     */
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.1");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    /**
     * Метод ниже нам необходим, чтобы динамически задавать адрес базы данных (ее URL)
     */
    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);

    }

}
