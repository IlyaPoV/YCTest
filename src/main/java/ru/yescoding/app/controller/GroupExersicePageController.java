package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupExersicePageController {
    @GetMapping("/groupExercise")
    public String getGroupExersicePage(){
        return "groupExercise";
    }
}
