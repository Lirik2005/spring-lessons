package com.spring.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

/**
 * Это базовый класс для аудита наших сущностей (позволяет смотреть когда и кто что-то изменяли в базе данных).
 * Аннотация @MappedSuperclass нужна чтобы поля класса User унаследовали поля от AuditingEntity.
 * Чтобы унаследованные поля изменялись, нам нужны слушатели событий (Listeners). Для этого используем @EntityListeners
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingEntity<T extends Serializable> implements BaseEntity<T> {

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant modifiedAt;

    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String modifiedBy;

}
