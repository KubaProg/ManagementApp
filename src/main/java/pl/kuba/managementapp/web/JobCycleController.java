package pl.kuba.managementapp.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.kuba.managementapp.JobCycle.JobCycleService;
import pl.kuba.managementapp.web.dataHolders.DataHolder;

import java.time.LocalTime;


@Controller
public class JobCycleController {

    JobCycleService jobCycleService;
    DataHolder dataHolder;

    public JobCycleController(JobCycleService jobCycleService, DataHolder dataHolder) {
        this.jobCycleService = jobCycleService;
        this.dataHolder = dataHolder;
    }

    @PostMapping("/save")
    public String sendDataToInfoPage(@RequestParam String jobName,
                                     Model model
                                     )
    {
        dataHolder.setJobName(jobName);
        return "redirect:/fieldsList";
    }

    @PostMapping("/save2")
    public String sendDataToInfoPage2(@RequestParam String fieldName, Model model,
                                      RedirectAttributes redirectAttributes)
    {

        String time = jobCycleService.getTime();
        String jobName = dataHolder.getJobName();
        model.addAttribute("fieldName", fieldName);
        model.addAttribute("time", time);
        model.addAttribute("jobName", jobName);
        return "jobStartPage";
    }

    @PostMapping("/end")
    public String sendDataToEndPage(Model model){

        String time = jobCycleService.getTime();
        String jobName = dataHolder.getJobName();
        model.addAttribute("time",time);
        model.addAttribute("jobName", jobName);
        return "jobEndPage";
    }



}
