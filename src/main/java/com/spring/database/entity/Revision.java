package com.spring.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Аннотация @RevisionEntity указывает, что эта сущность для ревизии и должна быть одна на все приложение.
 * Для того чтобы отслеживать ревизии мы должны поставить аннотацию @Audited над всеми нашими сущностями
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RevisionEntity
public class Revision {

    /**
     * Поля id и timestamp обязательны и должны быть помечены соответствующими аннотациями @RevisionNumber и @RevisionTimestamp
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Integer id;

    @RevisionTimestamp
    private Long timestamp;

}
