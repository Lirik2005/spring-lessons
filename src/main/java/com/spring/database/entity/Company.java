package com.spring.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

/**
 * Аннотация @Entity это из Hibernate и является обязательной для любой сущности.
 * Аннотация @NamedQuery используется для создания именованных запросов в БД. Именования параметров запроса должно совпадать с названиями
 * полей в сущности.
 * Если name (в данном случае findByName) совпадает с названием метода в CompanyRepository (там тоже есть метод findByName), то указанная
 * аннотация @NamedQuery имеет приоритет и будет выполняться именно тот запрос, который в ней указан.
 * Если названия поля класса не соответствует значению в запросе, то в CompanyRepository в методе findByName надо поставить около
 * принимаемого значения аннотацию @Param и указать название поля
 */

@NamedQuery(
        name = "Company.findByName",
        query = "select c from Company c  where lower(c.name) = lower(:name2)"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "company")
public class Company implements BaseEntity<Integer> {

    /**
     * Эти аннотации тоже обязательны. В данном случае id будет автоматически генерироваться.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Это поле у нас используется для таблицы company_locales.
     */

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locales", joinColumns = @JoinColumn(name = "company_id"))
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> locales = new HashMap<>();
}
