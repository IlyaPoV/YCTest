package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.yescoding.app.model.dto.GroupDto;
import ru.yescoding.app.service.GroupsService;

import java.util.List;
import java.util.Map;

@Controller
public class GroupsController {
    private final GroupsService groupsService;

    public GroupsController(GroupsService groupsService){
        this.groupsService = groupsService;
    }

    @ModelAttribute
    public void add(Model model) {
        model.addAttribute(
                "groups",
                groupsService.getGroups()
        );
    }

    @GetMapping("/groups")
    public String getGroups(){
        return "group-page";
    }
}
