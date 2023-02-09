package pl.kuba.managementapp.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kuba.managementapp.Field.FieldService;
import pl.kuba.managementapp.Job.JobService;
import pl.kuba.managementapp.JobCycle.JobCycle;
import pl.kuba.managementapp.JobCycle.JobCycleService;
import pl.kuba.managementapp.PickResult.PickResult;
import pl.kuba.managementapp.PickResult.PickResultService;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserRepository;
import pl.kuba.managementapp.User.UserService;

@Controller
public class JobCycleController {
    private final JobCycle jobCycle;
    private final PickResult pickResult;
    private final JobService jobService;
    private final FieldService fieldService;
    private final JobCycleService jobCycleService;
    private final PickResultService pickResultService;
    private final UserService userService;
    private final UserRepository userRepository;

    public JobCycleController(JobCycleService jobCycleService,
                              JobCycle jobCycle, JobService jobService,
                              UserService userService, FieldService fieldService,
                              PickResult pickResult, PickResultService pickResultService,
                              UserRepository userRepository)
    {
        this.jobCycleService = jobCycleService;
        this.jobCycle = jobCycle;
        this.jobService = jobService;
        this.userService = userService;
        this.fieldService = fieldService;
        this.pickResult = pickResult;
        this.userRepository = userRepository;
        this.pickResultService = pickResultService;
    }

    @PostMapping("/saveJobAndUser")
    public String saveJobAndUser(@RequestParam String jobName, Model model)
    {
        User currentUser = userService.findCurrentUser();
        jobCycle.setUser(currentUser);
        jobCycle.setJob(jobService.findByName(jobName));

        if(jobName.equals("Zbieranie")){
            pickResult.setUser(userService.findCurrentUser());
        }

        return "redirect:/fieldsList";
    }

    @PostMapping("/saveFieldAndTime")
    public String saveFieldAndTime(@RequestParam String fieldName,
                                      Model model)
    {
        String time = jobCycleService.getTime();
        jobCycle.setStartTime(time);
        jobCycle.setField(fieldService.findByName(fieldName));
        model.addAttribute("fieldName", fieldName);
        model.addAttribute("time", time);
        model.addAttribute("jobName", jobCycle.getJob().getName());
        jobCycleService.saveJobCycle(jobCycle);
        pickResult.setFieldName(fieldName);
        pickResultService.savePickResult(pickResult);
        return "jobStartPage";
    }

    @PostMapping("/endWork")
    public String saveCompletedJobCycle(Model model){
        JobCycle jobCycle = jobCycleService.findRecentlyStartedJobCycle();
        String time = jobCycleService.getTime();
        jobCycle.setEndTime(time);
        jobCycleService.saveJobCycle(jobCycle);

        if(jobCycle.getJob().getName().equals("Zbieranie"))
        {
            return "weightForm";
        }

        model.addAttribute("time",time);
        model.addAttribute("jobName", jobCycle.getJob().getName());
        return "jobEndPage";
    }

}
