package pl.kuba.managementapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String hello(){
        return "helloPage";
    }

    @PostMapping("/adminsPanel")
    public String adminsPanel(){
        return "adminsPanel";
    }

    @GetMapping("/choice")
    public String choiceForm(){
        return "choice-form";
    }

}
