package com.spring.integration.database.repository;

import com.spring.database.entity.Company;
import com.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Аннотация @Transactional нужна для управления транзакцией. Если ее убрать, то тест упадет, так как не сможет получить данные о locales.
 * При этом лучше использовать эту аннотацию от Spring. И мы ее вынесем в аннотацию @IT. Теперь все тесты будут транзакционными.
 * <p>
 * Аннотация @Commit произведет изменения базы данных и запишет компанию из теста save().
 * По умолчанию, все подобные операции заканчиваются роллбэком.
 */
@IT
@RequiredArgsConstructor
class CompanyRepositoryTest {

    private final EntityManager entityManager;

    /**
     * TransactionTemplate служит для ручной настройки транзакций и не пользоваться аннотацией @Transactional.
     * Используется нечасто!!!
     */

    private final TransactionTemplate transactionTemplate;

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(myTransaction -> {
            Company company = entityManager.find(Company.class, 1);

            assertNotNull(company);
            assertEquals(2, company.getLocales().size());
        });

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