package pl.kuba.managementapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kuba.managementapp.Job.Job;
import pl.kuba.managementapp.Job.JobService;
import pl.kuba.managementapp.JobCycle.JobCycle;
import pl.kuba.managementapp.JobCycle.JobCycleService;

import java.util.List;

@Controller
public class JobController {

    private final JobService jobService;
    private final JobCycleService jobCycleService;

    public JobController(JobService jobService, JobCycleService jobCycleService) {
        this.jobService = jobService;
        this.jobCycleService = jobCycleService;
    }

    @GetMapping("/jobList")
    public String getJobList(Model model){
        List<Job> jobs = jobService.findAllJobs();
        JobCycle jobCycle = jobCycleService.findRecentlyStartedJobCycle();
        model.addAttribute("jobs", jobs);
        model.addAttribute("jobCycle", jobCycle);
        return "jobList";
    }

}
