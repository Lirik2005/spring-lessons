package com.spring.service;

import com.spring.database.entity.Company;
import com.spring.database.repository.CrudRepository;
import com.spring.dto.CompanyReadDto;
import com.spring.listener.entity.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Здесь мы пишем тест на CompanyService. Чтобы не привязываться к его зависимостям, мы просто скопировали их в тест и пометили
 * аннотацией @Mock. После чего заинжектили эти моки в CompanyService.
 * Аннотация @ExtendWith(MockitoExtension.class) необходима, чтобы получить возможность создавать моки.
 */

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;


    @Mock
    private CrudRepository<Integer, Company> companyRepository;
    @Mock
    private UserService userService;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        Mockito.doReturn(Optional.of(new Company(COMPANY_ID)))
               .when(companyRepository).findById(COMPANY_ID);

        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);

        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

        Mockito.verify(eventPublisher).publishEvent(Mockito.any(EntityEvent.class));
        Mockito.verifyNoMoreInteractions(eventPublisher, userService);
    }
}