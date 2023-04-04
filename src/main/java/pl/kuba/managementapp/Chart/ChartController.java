package pl.kuba.managementapp.Chart;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kuba.managementapp.Field.Field;
import pl.kuba.managementapp.Field.FieldRepository;
import pl.kuba.managementapp.Field.Fields;
import pl.kuba.managementapp.Salary.SalaryService;

@Controller
public class ChartController {
    private final DataPointService dataPointService;
    private final FieldRepository fieldRepository;

    public ChartController(DataPointService dataPointService, FieldRepository fieldRepository) {
        this.dataPointService = dataPointService;
        this.fieldRepository = fieldRepository;
    }

    @GetMapping("/showFruitList")
    public String fruitList(Model model) {
        List<String> fields = fieldRepository.findAll()
                .stream()
                .map(Field::getName)
                .toList();
        model.addAttribute("fields",fields);
        return "fruitList";
    }

    @PostMapping("/displayScatteredChart")
    public String bubbleChart(Model model, @RequestParam String field) {
        String dataPointsJson;
        try {
            dataPointsJson = dataPointService.createDataPointsJson(field);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("dataPoints", dataPointsJson);
        model.addAttribute("fruit", field);
        return "scatteredChart";
    }



}