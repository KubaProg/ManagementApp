package pl.kuba.managementapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kuba.managementapp.Job.Job;
import pl.kuba.managementapp.Job.JobService;

import java.util.List;

@Controller
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobList")
    public String getJobList(Model model){
        List<Job> jobs = jobService.findAllJobs();
        model.addAttribute("jobs", jobs);
        return "jobList";
    }

}
