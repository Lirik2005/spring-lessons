package org.example.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

/**
 * Топаем в application.xml и там инициализируем бин этого класс
 * Интерфейс PriorityOrdered и его метод getOrder() управляют последовательностью создания бина
 */
public class LogBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
