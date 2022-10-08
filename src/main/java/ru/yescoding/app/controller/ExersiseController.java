package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExersiseController {
    @GetMapping("/exercises")
    public String getExercise(){
        return "exercise-page";
    }
}
