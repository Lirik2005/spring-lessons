package com.spring.dto;

import java.time.LocalDate;

/**
 * Этот класс и есть проекция нашей сущности User. Главное требование в том, чтобы поля здесь назывались так же, как и в User.
 * Тогда наш repository сможет сам смапить информацию в PersonalInfo, полученную из таблицы users
 */
public record PersonalInfo(String firstname, String lastname, LocalDate birthDate) {

}
