package ru.yescoding.app.controller;

import com.sun.net.httpserver.Authenticator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.yescoding.app.service.GroupsService;
import ru.yescoding.app.service.SuccessService;

@Controller
public class SuccessController {

    private final SuccessService successService;

    public SuccessController(SuccessService successService){
        this.successService = successService;
    }

    @ModelAttribute
    public void add(Model model) {
        model.addAttribute(
                "success",
                successService.getSuccess()
        );
    }
    @GetMapping("/successes")
    public String getSuccessesForm(){
        return "success-page";
    }

}
