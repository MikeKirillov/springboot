package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller// mark class as REST-controller
@RestController
@EnableAutoConfiguration// mark as self configured
public class SampleController {
    @JsonProperty("someSentence")
    static String someSentence;

    public static String getSomeSentence() {
        return someSentence;
    }

    public static void setSomeSentence(String someSentence) {
        SampleController.someSentence = someSentence;
    }

    // Сообщаем сервису, что он должен работать по URL-адресу /hello
    // например, http://localhost:8080/hello
    // и говорим, что у него должно быть тело ответа (т.е. ответ не пустой)
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String helloCourse() {
        return "Hello guys!";
    }

    @RequestMapping(value = "/salam/create", method = RequestMethod.POST)
//    @PostMapping("/salam/create")
//    @ResponseBody// not needed to use cuz of @RestController. using only with @Controller
    public void setSalam(@RequestBody String someSentence) {
        setSomeSentence(someSentence);
    }

    @RequestMapping(value = "/salam/privet", method = RequestMethod.GET)
//    @GetMapping("/salam/privet")
//    @ResponseBody
    public String getSalam() {
        if (someSentence == null) {
            return "it's Britney, bitch";
        } else {
            return getSomeSentence();
        }
    }

    public static void main(String[] args) {
        // метод, который запускает наше Spring-приложение,
        // от main больше ничего не надо
        SpringApplication.run(SampleController.class, args);
    }
}
