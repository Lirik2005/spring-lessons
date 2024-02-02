package com.spring.integration.service;

import com.spring.database.pool.ConnectionPool;
import com.spring.integration.annotation.IT;
import com.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

/**
 * Так как сейчас у нас один контекст для всех интеграционных тестов, то можно использовать аннотацию @DirtiesContext и предавать туда
 * classMode (у classMode есть различные варианты). Это значит, что после каждого теста контекст будет помечен как dirty и его нельзя
 * переиспользовать в других тестах.
 * Эту аннотацию можно ставить и над конкретным тестом, передавая уже methodMode!
 * <p>
 * Но такой функционал рекомендуется использовать, только если в тестах ВРУЧНУЮ портится контекст!!!
 */
@IT
@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    void test() {
        System.out.println();
    }

    @Test
    void test2() {
        System.out.println();
    }
}
