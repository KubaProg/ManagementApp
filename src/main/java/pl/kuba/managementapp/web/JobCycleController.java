package pl.kuba.managementapp.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kuba.managementapp.Field.FieldService;
import pl.kuba.managementapp.Job.JobService;
import pl.kuba.managementapp.JobCycle.JobCycle;
import pl.kuba.managementapp.JobCycle.JobCycleService;
import pl.kuba.managementapp.JobResult.JobResult;
import pl.kuba.managementapp.JobResult.JobResultService;
import pl.kuba.managementapp.PickResult.PickResult;
import pl.kuba.managementapp.PickResult.PickResultService;
import pl.kuba.managementapp.Salary.SalaryService;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserRepository;
import pl.kuba.managementapp.User.UserService;

@Controller
public class JobCycleController {
    private final JobCycle jobCycle;
    private final PickResult pickResult;
    private final JobResult jobResult;
    private final JobService jobService;
    private final FieldService fieldService;
    private final JobCycleService jobCycleService;
    private final PickResultService pickResultService;
    private final UserService userService;
    private final JobResultService jobResultService;
    private final SalaryService salaryService;

    public JobCycleController(JobCycleService jobCycleService,
                              JobService jobService, JobCycle jobCycle,
                              UserService userService, FieldService fieldService,
                              PickResult pickResult, PickResultService pickResultService,
                              UserRepository userRepository, JobResult jobResult,
                              JobResultService jobResultService, SalaryService salaryService)
    {
        this.jobCycleService = jobCycleService;
        this.jobCycle = jobCycle;
        this.jobService = jobService;
        this.userService = userService;
        this.fieldService = fieldService;
        this.pickResult = pickResult;
        this.jobResultService = jobResultService;
        this.pickResultService = pickResultService;
        this.jobResult = jobResult;
        this.salaryService = salaryService;
    }

    @PostMapping("/saveJobAndUser")
    public String saveJobAndUser(@RequestParam String jobName, Model model)
    {
        User currentUser = userService.findCurrentUser();
        jobCycle.setUser(currentUser);
        jobCycle.setJob(jobService.findByName(jobName));

        if(jobName.equals("Zbieranie")){
            pickResult.setUser(userService.findCurrentUser());
        }else{
            jobResult.setUser(userService.findCurrentUser());
            jobResult.setJobName(jobName);
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
        // Saving JobCycle object to db
        JobCycle jobCycleToSave = jobCycleService.createObject(jobCycle.getId(), jobCycle.getStartTime(),
                jobCycle.getJob(), jobCycle.getUser(),
                jobCycle.getField());
        jobCycleService.saveJobCycle(jobCycleToSave);

        //Saving PickResult object to db
        if(jobCycle.getJob().getName().equals("Zbieranie"))
        {
            pickResult.setFieldName(fieldName);
            PickResult pickResultToSave = pickResultService.createObject(
                    pickResult.getId(),
                    pickResult.getFieldName(),
                    pickResult.getUser());
            pickResultService.savePickResult(pickResultToSave);
        } // Saving JobResult ( different from picking )
        else{
            jobResult.setFieldName(fieldName);
            JobResult jobResultToSave = jobResultService.createObject(
                    jobResult.getId(),
                    jobResult.getJobName(),
                    jobResult.getFieldName(),
                    jobResult.getUser()
            );
            jobResultService.saveJobResult(jobResultToSave);
        }

        model.addAttribute("fieldName", fieldName);
        model.addAttribute("time", time);
        model.addAttribute("jobName", jobCycle.getJob().getName());

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
        }else{
            JobResult jobResult = jobResultService.findRecentJobResult(userService.findCurrentUserId());
            JobResult jobResultToSave = jobResultService.countTimeAndMoney(jobResult, userService.findCurrentUserId());
            jobResultService.saveJobResult(jobResultToSave);
        }

        salaryService.updateSalary();

        model.addAttribute("time",time);
        model.addAttribute("jobName", jobCycle.getJob().getName());
        return "jobEndPage";
    }

}
