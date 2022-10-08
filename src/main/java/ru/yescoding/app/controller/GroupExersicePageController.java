package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupExersicePageController {
    @GetMapping("/group-exercise")
    public String getGroupExercisePage(){
        return "groups-exercise-page";
    }
}
