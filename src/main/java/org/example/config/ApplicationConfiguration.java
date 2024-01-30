package org.example.config;

import org.example.database.repository.CrudRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.*;

/**
 * Аннотация @Configuration позволяет нам подключать какие-либо конфигурации к приложению. Позволяет отказаться от application.xml.
 * Теперь все настройки делаем тут.
 * Аннотация @PropertySource("classpath:application.properties") нужна для доступа к нашему application.properties.
 * Аннотация @ComponentScan необходима для поиска наших аннотаций @Component или фильтров.
 */
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
