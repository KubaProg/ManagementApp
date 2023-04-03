package pl.kuba.managementapp.Chart;

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
    private final DataPointService dataPointService;

    public ChartController(SalaryService salaryService, DataPointService dataPointService) {
        this.salaryService = salaryService;
        this.dataPointService = dataPointService;
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

        dataPointService.createDataPointsList("truskawy");
        model.addAttribute("dataPoints", dataPointsJson);
        return "scatteredChart";
    }


}