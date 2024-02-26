package com.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
     */
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView) {
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
