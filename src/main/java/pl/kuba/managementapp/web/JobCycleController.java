package pl.kuba.managementapp.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kuba.managementapp.Field.FieldService;
import pl.kuba.managementapp.Job.JobService;
import pl.kuba.managementapp.JobCycle.JobCycle;
import pl.kuba.managementapp.JobCycle.JobCycleService;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserService;
import pl.kuba.managementapp.web.dataHolders.DataHolder;

@Controller
public class JobCycleController {
    private final DataHolder dataHolder;
    private final JobCycle jobCycle;
    private final JobService jobService;
    private final FieldService fieldService;

    private final JobCycleService jobCycleService;
    private final UserService userService;

    public JobCycleController(JobCycleService jobCycleService, DataHolder dataHolder,
                              JobCycle jobCycle, JobService jobService,
                              UserService userService, FieldService fieldService)
    {
        this.jobCycleService = jobCycleService;
        this.dataHolder = dataHolder;
        this.jobCycle = jobCycle;
        this.jobService = jobService;
        this.userService = userService;
        this.fieldService = fieldService;
    }

    @PostMapping("/saveJobAndUser")
    public String saveJobAndUser(@RequestParam String jobName, Model model)
    {
        User currentUser = userService.findCurrentUser();
        jobCycle.setUser(currentUser);
        jobCycle.setJob(jobService.findByName(jobName));
        dataHolder.setJobName(jobName);
        return "redirect:/fieldsList";
    }

    @PostMapping("/saveFieldAndTime")
    public String saveFieldAndTime(@RequestParam String fieldName,
                                      Model model)
    {
        String time = jobCycleService.getTime();
        String jobName = dataHolder.getJobName();
        jobCycle.setStartTime(time);
        jobCycle.setField(fieldService.findByName(fieldName));
        model.addAttribute("fieldName", fieldName);
        model.addAttribute("time", time);
        model.addAttribute("jobName", jobName);
        jobCycleService.saveJobCycle(jobCycle);
        return "jobStartPage";
    }

    @PostMapping("/endWork")
    public String saveCompletedJobCycle(Model model){
        JobCycle jobCycle = jobCycleService.findRecentlyStartedJobCycle();
        String time = jobCycleService.getTime();
        jobCycle.setEndTime(time);
        jobCycleService.saveJobCycle(jobCycle);
        model.addAttribute("time",time);
        model.addAttribute("jobName", jobCycle.getJob().getName());
        return "jobEndPage";
    }


}
