package com.spring.http.controller;

import com.spring.database.entity.Role;
import com.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Аннотация @Controller указывает на то, что наш класс это Spring Controller.
 * Аннотация @RequestMapping в основном используется для указания общего префикса для всех наших эндпоинтов.
 * Аннотация @SessionAttributes({"user"}) устанавливает сессионный атрибут со значением user
 */

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

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
     * <p>
     * Аннотация @ModelAttribute позволяет нам динамически заполнять нашего юзера. Более того, ее можно и не писать (и так работает)
     */
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello(Model model, HttpServletRequest request, @ModelAttribute("userReadDto") UserReadDto userReadDto) {

        model.addAttribute("user", new UserReadDto(1L, "Ivan"));

        return "greeting/hello";
    }

    /**
     * Здесь аннотация @SessionAttribute("user") ищет атрибут user. Если его нет, то получаем ошибку. Поэтому сначала вызываем /hello,
     * где появляется наш user и только тогда у нас сработает /bye
     */

    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") UserReadDto user, Model model) {

        return "greeting/bye";
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
