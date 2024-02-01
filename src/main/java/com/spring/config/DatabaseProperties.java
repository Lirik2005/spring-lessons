package com.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * Данный класс нужен, чтобы мапить поля из нашего application.yaml. Поля в классе должны именоваться (и полностью совпадать) точно также,
 * как и в application.yaml. И тогда Spring без проблем формат данных application.yaml в объект DatabaseProperties.
 * У класса DatabaseProperties должны быть геттеры, сеттеры и аннотация @ConstructorBinding для работы с конструктором!!!
 * Чтобы смапить application.yaml на DatabaseProperties.class надо создать бин этого класса. Мы это делаем в JpaConfiguration.class.
 * Второй вариант - это указать @ConfigurationProperties(prefix = "database") прямо над DatabaseProperties.class и дополнительно повесить
 * аннотацию @Component.
 * Вместо @Component над классом DatabaseProperties.class можно поставить аннотацию @ConfigurationPropertiesScan над классом
 * ApplicationRunner.class
 * <p>
 * Важно!!! Этот класс должен быть immutable, поэтому его можно записать в record
 * В record нам также не потребуется аннотация @ConstructorBinding
 */


@ConfigurationProperties(prefix = "database")
public record DatabaseProperties(String username,
                                 String password,
                                 String driver,
                                 String url,
                                 String hosts,
                                 PoolProperties pool,
                                 List<PoolProperties> pools,
                                 Map<String, Object> properties) {


    public static record PoolProperties(Integer size,
                                        Integer timeout) {

    }
}
