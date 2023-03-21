package pl.kuba.managementapp.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kuba.managementapp.Salary.Salary;
import pl.kuba.managementapp.Salary.SalaryService;

@Controller
public class ChartController {

    private final SalaryService salaryService;

    public ChartController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/displayBarGraph")
    public String barGraph(Model model) {
        List<Salary> allSalaries = salaryService.findAllSalaries();


        List<Long> ids = allSalaries.stream()
                .map(Salary::getId)
                .toList();

        List<Double> salaries = allSalaries.stream()
                .map(Salary::getSalary_value)
                .toList();

        Map<Long, Double> surveyMap = IntStream.range(0, ids.size())
                .boxed()
                .collect(Collectors.toMap(ids::get, salaries::get));


        model.addAttribute("surveyMap", surveyMap);
        return "barGraph";
    }

    @GetMapping("/displayPieChart")
    public String pieChart(Model model) {
        model.addAttribute("pass", 50);
        model.addAttribute("fail", 50);
        return "pieChart";
    }

}