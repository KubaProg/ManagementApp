package pl.kuba.managementapp.web;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.kuba.managementapp.Field.FieldRepository;
import pl.kuba.managementapp.Job.JobRepository;
import pl.kuba.managementapp.JobCycle.JobCycle;
import pl.kuba.managementapp.JobCycle.JobCycleRepository;
import pl.kuba.managementapp.JobCycle.JobCycleService;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserRepository;
import pl.kuba.managementapp.web.dataHolders.DataHolder;

import java.time.LocalTime;
import java.util.Optional;


@Controller
public class JobCycleController {

    JobCycleService jobCycleService;
    DataHolder dataHolder;
    JobCycle jobCycle;

    private final JobRepository jobRepository;
    private final FieldRepository fieldRepository;
    private final JobCycleRepository jobCycleRepository;
    private final UserRepository userRepository;

    public JobCycleController(JobCycleService jobCycleService, DataHolder dataHolder, JobCycle jobCycle,
                              FieldRepository fieldRepository, JobRepository jobRepository,
                              JobCycleRepository jobCycleRepository,
                              UserRepository userRepository) {
        this.jobCycleService = jobCycleService;
        this.dataHolder = dataHolder;
        this.jobCycle = jobCycle;
        this.fieldRepository = fieldRepository;
        this.jobCycleRepository = jobCycleRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/save")
    public String sendDataToInfoPage(@RequestParam String jobName, Model model)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(name);
        User user1 = user.get();
        jobCycle.setUser(user1);
        jobCycle.setJob(jobRepository.findByName(jobName));
        dataHolder.setJobName(jobName);
        return "redirect:/fieldsList";
    }

    @PostMapping("/save2")
    public String sendDataToInfoPage2(@RequestParam String fieldName,
                                      Model model)
    {
        String time = jobCycleService.getTime();
        String jobName = dataHolder.getJobName();
        jobCycle.setStartTime(time);
        jobCycle.setField(fieldRepository.findByName(fieldName));
        model.addAttribute("fieldName", fieldName);
        model.addAttribute("time", time);
        model.addAttribute("jobName", jobName);
        jobCycleRepository.save(jobCycle);
        return "jobStartPage";
    }

    @PostMapping("/end")
    public String sendDataToEndPage(Model model){
        String time = jobCycleService.getTime();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userRepository.findIdByEmail(name);
        JobCycle jobCycle = jobCycleRepository.findByUserIdAndEndTimeIsNull(userId);
        jobCycle.setEndTime(time);
        jobCycleRepository.save(jobCycle);
        String jobName = dataHolder.getJobName();
        model.addAttribute("time",time);
        model.addAttribute("jobName", jobName);
        return "jobEndPage";
    }


}
