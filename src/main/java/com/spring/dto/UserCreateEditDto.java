package com.spring.dto;

import com.spring.database.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Аннотация @DateTimeFormat может быть заменой для записи в application.yaml строк format.date: iso
 */
@Value
@FieldNameConstants
public class UserCreateEditDto {

    @Email
    String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    @NotNull
    @Size(min = 3, max = 64)
    String firstname;

    @NotNull
    String lastname;

    Role role;

    Integer companyId;
}
