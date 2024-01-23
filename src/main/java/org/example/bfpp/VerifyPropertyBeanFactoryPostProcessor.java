package org.example.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * Топаем в application.xml и там инициализируем бин этого класс
 * Интерфейс Ordered и его метод getOrder() управляют последовательностью создания бина
 * Здесь приоритет будет ниже чем у LogBeanFactoryPostProcessor
 */
public class VerifyPropertyBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
