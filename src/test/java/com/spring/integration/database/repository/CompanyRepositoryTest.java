package com.spring.integration.database.repository;

import com.spring.database.entity.Company;
import com.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Аннотация @Transactional нужна для управления транзакцией. Если ее убрать, то тест упадет, так как не сможет получить данные о locales.
 * При этом лучше использовать эту аннотацию от Spring.
 *
 * Аннотация @Commit произведет изменения базы данных и запишет компанию из теста save().
 * По умолчанию, все подобные операции заканчиваются роллбэком.
 */
@IT
@RequiredArgsConstructor
@Transactional
@Commit
class CompanyRepositoryTest {

    private final EntityManager entityManager;

    @Test
    void findById() {
        Company company = entityManager.find(Company.class, 1);

        assertNotNull(company);
        assertEquals(2, company.getLocales().size());
    }

    @Test
    void save() {
        Company apple = Company.builder()
                               .name("Apple")
                               .locales(Map.of(
                                       "ru", "Apple описание",
                                       "en", "Apple description"
                               ))
                               .build();
        entityManager.persist(apple);
        assertNotNull(apple.getId());
    }

}