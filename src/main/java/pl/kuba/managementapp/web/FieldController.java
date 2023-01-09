package pl.kuba.managementapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FieldController {

    @GetMapping("/fieldsList")
    public String fields(Model model){
        return "fieldsList";
    }

}
