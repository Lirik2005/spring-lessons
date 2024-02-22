package com.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * Аннотация @Controller указывает на то, что наш класс это Spring Controller
 */

@Controller
public class GreetingController {

    public ModelAndView hello(ModelAndView modelAndView) {
        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }

    public ModelAndView bye() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }
}
