package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@Controller
public class GroupController {
    @GetMapping("/groups")
    public String getGroup(@RequestHeader(required = false) Map<String, String> headers){
        return "group-page";
    }
}
