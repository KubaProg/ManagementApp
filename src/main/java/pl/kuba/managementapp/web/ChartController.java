package pl.kuba.managementapp.web;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kuba.managementapp.Salary.SalaryService;

@Controller
public class ChartController {

    private final SalaryService salaryService;

    public ChartController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/displayScatteredChart")
    public String bubbleChart(Model model) {
        List<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint(134.5,5,"Michalina", "Zareba", 2));
        dataPoints.add(new DataPoint(151.1,8.5,"Jakub", "Opiełka", 1));

        ObjectMapper objectMapper = new ObjectMapper();
        String dataPointsJson = "";
        try {
            dataPointsJson = objectMapper.writeValueAsString(dataPoints);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("dataPoints", dataPointsJson);
        return "scatteredChart";
    }


}