package com.spring.dto;

import com.spring.database.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Аннотация @DateTimeFormat может быть заменой для записи в application.yaml строк format.date: iso
 */
@Value
@FieldNameConstants
public class UserCreateEditDto {

    String username;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    String firstname;

    String lastname;

    Role role;

    Integer companyId;
}
