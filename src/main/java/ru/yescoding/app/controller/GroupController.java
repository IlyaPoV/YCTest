package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {
    @GetMapping("/groups")
    public String getGroup(){
        return "group-page";
    }
}
