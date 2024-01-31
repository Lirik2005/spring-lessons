package org.example.config;

import org.example.database.pool.ConnectionPool;
import org.example.database.repository.CrudRepository;
import org.example.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.web.config.WebConfiguration;

import static org.springframework.context.annotation.ComponentScan.Filter;

/**
 * Аннотация @Configuration позволяет нам подключать какие-либо конфигурации к приложению. Позволяет отказаться от application.xml.
 * Теперь все настройки делаем тут.
 * Аннотация @PropertySource("classpath:application.properties") нужна для доступа к нашему application.properties.
 * Аннотация @ComponentScan необходима для поиска наших аннотаций @Component или фильтров.
 * Аннотация @ImportResource("classpath:application.xml") позволяет комбинировать Java-Based configuration и XML-Based configuration.
 * Однако, на практике такое не используется!!!
 * Аннотация @Import() используется для подключения других классов конфигурации.
 */

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class) // Наш скан не сканирует этот пакет, а аннотация позволяет получать конфигурации
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.example",
        useDefaultFilters = false,  // убирает дефолтный сканер по аннотации @Component
        includeFilters = {          // добавляем свои фильтры
                @Filter(type = FilterType.ANNOTATION, value = Component.class), // тут говорим, что ищем аннотацию @Component
                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class), // тут говорим, что ищем CrudRepository.class
                @Filter(type = FilterType.REGEX, pattern = "org\\..+Repository") // тут у нас скан по регулярке (то есть по пакетам)
        })
public class ApplicationConfiguration {

    /**
     * Аннотация @Bean делает из нашего объекта бин. То есть, теперь ConnectionPool, который возвращает нам метод, является бином с именем
     * pool2.
     * Также над методом аннотацию @Scope, чтобы создавать не синглтон-бин, а какждый раз новый бин.
     * С помощью аннотации @Value мы можем в бин сразу инжектить другой бин.
     */
    @Bean
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 25);
    }

    @Bean
    public UserRepository userRepository2(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    /**
     * Также заинжектить бин мы можем просто обратившись к методу, который создает нам бин. Такое сработает только, если есть бины и они
     * зависят друг от друга.
     */
    @Bean
    public UserRepository userRepository3() {
        return new UserRepository(pool3());
    }
}
