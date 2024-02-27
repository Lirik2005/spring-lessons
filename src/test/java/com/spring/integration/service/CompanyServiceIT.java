package com.spring.integration.service;

import com.spring.config.DatabaseProperties;
import com.spring.dto.CompanyReadDto;
import com.spring.integration.annotation.IT;
import com.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Над тестовым классом должна быть аннотация @ExtendWith(SpringExtension.class) используется для создания интеграционных тестов в Spring.
 * Над тестовым классом должна быть аннотация @ContextConfiguration позволяет указать какой Spring Application Context мы будем
 * использовать в нашем тесте.
 * Параметр 'initializers = ConfigDataApplicationContextInitializer.class' необходим, чтобы наши тесты поняли про файл application-test
 * .yaml,
 * так как по умолчанию тесты работают только application.properties.
 * <p>
 * !!!НО САМЫМ ПРОСТЫМ И ВЕРНЫМ РЕШЕНИЕМ БУДЕТ ИСПОЛЬЗОВАТЬ АННОТАЦИЮ @SpringBootTest!!!
 * <p>
 * Чтобы получать настройки из application-test.yaml, мы ставим аннотацию @ActiveProfiles("test"). Это значит, что сейчас мы смотрим на
 * resource из пакета test. Таким образом, мы возьмем указанные параметры из application-test.yaml, а недостающие параметры придут из
 * application.yaml в пакете src.
 * <p>
 * Сделали свою аннотацию @IT, которую можно будет ставить над всеми тестами и не лепить кучу других аннотаций над ними.
 * Также ставим аннотацию @RequiredArgsConstructor и делаем CompanyService и DatabaseProperties 'final', чтобы не использовать над ними
 * аннотацию @Autowired. Чтобы это заработало, ставим аннотацию @TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL).
 * <p>
 * Чтобы не использовать аннотацию @TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) в тестовые ресурсы можно положить
 * файл spring.properties и сделать там запись 'spring.test.constructor.autowire.mode=all'
 */

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ApplicationRunner.class, initializers = ConfigDataApplicationContextInitializer.class)
//@SpringBootTest
//@ActiveProfiles("test")
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@IT
@RequiredArgsConstructor
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;


    //    @Autowired
    private final CompanyService companyService;
    //    @Autowired
    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID, null);

        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

}
