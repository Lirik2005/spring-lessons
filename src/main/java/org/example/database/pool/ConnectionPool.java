package org.example.database.pool;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс InitializingBean также позволяет нам докрутить наш бин также, как и метод init(). При этом сначала вызовется метод
 * afterPropertiesSet() и только потом метод init(). Использование данного интерфейса является нежелательным, так как нарушается
 * inversion of control. В данном случае лучше всего использовать init(). А еще лучше использовать аннотацию @PostConstruct.
 * Вызываться это все будет в следующей последовательности: сначала @PostConstruct, потом интерфейс InitializingBean и в конце метод init().
 * Методы init() и afterPropertiesSet() - методы инициализации (должны быть void и не принимать никаких параметров).
 */
public class ConnectionPool implements InitializingBean {

    private final String username;
    private final int poolSize;
    private final List<Object> args;
    private Map<String, Object> properties;

    public ConnectionPool(String username,
                          int poolSize,
                          List<Object> args,
                          Map<String, Object> properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * Вызывается сразу после конструкторов и сеттеров
     */
    @PostConstruct
    private void init() {
        System.out.println("Init connection pool");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Properties set");
    }

    /**
     * Данный метод будет вызываться только у бинов, которые singleton, потому что бины prototype не хранятся в application context
     */
    @PreDestroy
    private void destroy() {
        System.out.println("Clean connection pool");
    }
}
