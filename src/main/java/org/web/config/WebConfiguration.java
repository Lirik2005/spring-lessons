package org.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Аннотация @Profile используется для того, что указать когда будет использоваться указанная конфигурация. В данном случае мы указали, что
 * данная конфигурация загрузится только тогда, когда мы передадим метку web.
 * Активировать профайлы мы можем, например, через application.properties.
 * Также можно активировать профайл через контекст (тут надо смотреть ApplicationRunner.class)
 * Лучше всего вызывать profile из application.properties!!!
 */

@Profile("web")
@Configuration
public class WebConfiguration {

}
