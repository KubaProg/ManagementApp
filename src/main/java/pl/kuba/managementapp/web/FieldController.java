package pl.kuba.managementapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kuba.managementapp.Field.FieldService;

import java.util.List;

@Controller
public class FieldController {

    FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/fieldsList")
    public String fields(Model model){
        List<String> names = fieldService.findAllFieldNames();
        model.addAttribute("names", names);
        return "fieldsList";
    }

}
