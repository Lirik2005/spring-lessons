package org.example.config;

import org.example.database.repository.CrudRepository;
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


}
