package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExersiseController {
    @GetMapping("/exersise")
    public String getExersise(){
        return "exersise";
    }
}
