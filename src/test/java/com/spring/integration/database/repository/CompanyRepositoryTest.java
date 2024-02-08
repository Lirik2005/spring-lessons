package com.spring.integration.database.repository;

import com.spring.database.entity.Company;
import com.spring.database.repository.CompanyRepository;
import com.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private static final Integer APPLE_ID = 11;

    private final EntityManager entityManager;

    /**
     * TransactionTemplate служит для ручной настройки транзакций и не пользоваться аннотацией @Transactional.
     * Используется нечасто!!!
     */

    private final TransactionTemplate transactionTemplate;

    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByName("Google");
        companyRepository.findALlByNameContainingIgnoreCase("a");
    }

    @Test
    void delete() {
        Optional<Company> maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(entity -> companyRepository.delete(entity));
        entityManager.flush(); // метод delete вызовется только после коммита транзакции или явного вызова метода flush()
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

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