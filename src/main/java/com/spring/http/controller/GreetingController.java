package com.spring.http.controller;

import com.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Аннотация @Controller указывает на то, что наш класс это Spring Controller.
 * Аннотация @RequestMapping в основном используется для указания общего префикса для всех наших эндпоинтов.
 * Аннотация @SessionAttributes({"user"}) устанавливает сессионный атрибут со значением user
 */

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    /**
     * Аннотация @RequestMapping позволяет нам смапить наш метод с конкретным URL и указать HTTP-метод.
     * Вместо аннотации @RequestMapping можно указать @GetMapping
     * Аннотация @RequestParam позволяет передавать параметры в url
     * Аннотация @RequestHeader позволяет передавать нужные хэдеры
     * Аннотация @CookieValue позволяет передавать нужные куки
     * По умолчанию аннотации @RequestParam, @RequestHeader и @CookieValue идут как обязательные!!!
     * <p>
     * Более того, если название параметра в аннотации совпадает с названием значения, то его можно опустить. Например, можно просто
     * написать @RequestParam Integer age
     * <p>
     * Аннотация @PathVariable нужна, чтобы подставлять переменные в гаш URL
     */
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("greeting/hello");

        modelAndView.addObject("user", new UserReadDto(1L, "Ivan"));

        return modelAndView;
    }

    /**
     * Здесь аннотация @SessionAttribute("user") ищет атрибут user. Если его нет, то получаем ошибку. Поэтому сначала вызываем /hello,
     * где появляется наш user и только тогда у нас сработает /bye
     */

    @GetMapping("/bye")
    public ModelAndView bye(@SessionAttribute("user") UserReadDto user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView,
                              @RequestParam("age") Integer age,
                              @RequestHeader("accept") String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable("id") Integer id) {
        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }
}
