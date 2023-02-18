package pl.kuba.managementapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kuba.managementapp.JobCycle.JobCycle;
import pl.kuba.managementapp.JobCycle.JobCycleService;
import pl.kuba.managementapp.PickResult.PickResult;
import pl.kuba.managementapp.PickResult.PickResultService;
import pl.kuba.managementapp.Salary.SalaryService;
import pl.kuba.managementapp.User.UserService;

@Controller
public class PickResultController {

    private final PickResult pickResult;
    private final JobCycle jobCycle;
    private final JobCycleService jobCycleService;
    private final PickResultService pickResultService;
    private final UserService userService;
    private final SalaryService salaryService;

    public PickResultController(PickResult pickResult,
                                JobCycleService jobCycleService,
                                PickResultService pickResultService,
                                UserService userService,
                                JobCycle jobCycle, SalaryService salaryService) {
        this.pickResult = pickResult;
        this.jobCycleService = jobCycleService;
        this.pickResultService = pickResultService;
        this.userService = userService;
        this.jobCycle = jobCycle;
        this.salaryService = salaryService;
    }

    @PostMapping("/weight")
    String getWeight(@RequestParam Double weight, Model model){
        PickResult pickResult = pickResultService.findRecentPickResult(userService.findCurrentUserId());
        pickResult.setWeight(weight);
        pickResult.setMoney(pickResultService.countMoney(weight));
        pickResultService.savePickResult(pickResult);

    model.addAttribute("jobName", "Picking");
    model.addAttribute("time", jobCycleService.getTime());

    salaryService.increaseSalary();

    return "jobEndPage";
    }

}
