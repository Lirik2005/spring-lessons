package com.spring.dto;

import java.time.LocalDate;

/**
 * Этот класс мы будем использовать в качестве фильтра для поиска наших пользователей
 */

public record UserFilter(String firstname, String lastname, LocalDate birthDate) {

}
