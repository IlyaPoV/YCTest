package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuccController {

    @GetMapping("/succes")
    public String showSuccForm(){
        return "success";
    }

}
