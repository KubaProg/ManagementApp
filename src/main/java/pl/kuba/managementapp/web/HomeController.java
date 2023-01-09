package pl.kuba.managementapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/loginPage")
    public String home(){
        return "loginPage";
    }

    @PostMapping("/adminsPanel")
    public String adminsPanel(){
        return "adminsPanel";
    }


}
