package pl.kuba.managementapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kuba.managementapp.Salary.SalaryService;

@Controller
public class HomeController {

    @GetMapping("/")
    public String hello(){
        return "helloPage";
    }

    @GetMapping("/choice")
    public String choiceForm(){
        return "choice-page";
    }


}
