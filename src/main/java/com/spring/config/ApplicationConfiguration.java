package com.spring.config;

import com.spring.database.pool.ConnectionPool;
import com.spring.database.repository.UserRepository;
import com.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

/**
 * Аннотация @Configuration позволяет нам подключать какие-либо конфигурации к приложению. Позволяет отказаться от application.xml.
 * Теперь все настройки делаем тут.
 * Аннотация @Import() используется для подключения других классов конфигурации.
 */

@Import(WebConfiguration.class) // Наш скан не сканирует этот пакет, а аннотация позволяет получать конфигурации
@Configuration(proxyBeanMethods = true)
public class ApplicationConfiguration {

    /**
     * Аннотация @Bean делает из нашего объекта бин. То есть, теперь ConnectionPool, который возвращает нам метод, является бином с именем
     * pool2.
     * Также над методом аннотацию @Scope, чтобы создавать не синглтон-бин, а каждый раз новый бин.
     * С помощью аннотации @Value мы можем в бин сразу инжектить другой бин.
     */
    @Bean("pool2")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${database.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 25);
    }

    /**
     * Аннотация @Profile используется для того, что указать когда будет использоваться указанный бин. В данном случае мы указали, что
     * бин userRepository2 появится только в продакшене или web-приложении.
     * Активировать профайлы мы можем, например, через application.properties.
     * Также можно активировать профайл через контекст (тут надо смотреть ApplicationRunner.class).
     * Лучше всего вызывать profile из application.properties!!!
     */

    @Bean
    @Profile("prod | web") //тут можно использовать логические операторы ! (не), & (и), | (или)
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
