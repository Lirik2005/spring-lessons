package com.spring.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

/**
 * Проекции предпочтительнее использовать через интерфейсы. Здесь нет полей, есть только методы. То есть пишем обычные геттеры (главное
 * чтобы совпадали имена полей, к которым мы их пишем).
 * Однако FullName у нас нет в таблице в качестве значения и мы можем использовать Spring Expression Language.
 */
public interface PersonalInfo2 {

    String getFirstname();
    String  getLastname();
    LocalDate getBirthDate();

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();

}
