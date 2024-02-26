package com.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Аннотация @Controller указывает на то, что наш класс это Spring Controller.
 * Аннотация @RequestMapping в основном используется для указания общего префикса для всех наших эндпоинтов.
 */

@Controller
@RequestMapping("/api/v1")
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
     *
     * Аннотация @PathVariable нужна, чтобы подставлять переменные в гаш URL
     */
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello/{id}")
    public ModelAndView hello(ModelAndView modelAndView,
                              @RequestParam("age") Integer age,
                              @RequestHeader("accept") String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable("id") Integer id) {
        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }

    //    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    @GetMapping("/bye")
    public ModelAndView bye() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }
}
