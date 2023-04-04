package pl.kuba.managementapp.Chart;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kuba.managementapp.Field.Fields;
import pl.kuba.managementapp.Salary.SalaryService;

@Controller
public class ChartController {
    private final DataPointService dataPointService;

    public ChartController(DataPointService dataPointService) {
        this.dataPointService = dataPointService;
    }

    @GetMapping("/displayScatteredChart")
    public String bubbleChart(Model model) {
        String dataPointsJson;
        try {
            dataPointsJson = dataPointService.createDataPointsJson("Grapes");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("dataPoints", dataPointsJson);
        return "scatteredChart";
    }


}