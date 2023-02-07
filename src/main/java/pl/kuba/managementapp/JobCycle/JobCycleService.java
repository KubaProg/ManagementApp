package pl.kuba.managementapp.JobCycle;

import org.springframework.stereotype.Service;
import pl.kuba.managementapp.User.UserService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service
public class JobCycleService {

    UserService userService;
    JobCycleRepository jobCycleRepository;

    public JobCycleService(JobCycleRepository jobCycleRepository, UserService userService) {
        this.jobCycleRepository = jobCycleRepository;
        this.userService = userService;
    }

    public String getTime(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = currentTime.format(formatter);
        return timeString;
    }

    public void saveJobCycle(JobCycle jobCycle){
        jobCycleRepository.save(jobCycle);
    }

    public JobCycle findRecentlyStartedJobCycle(){
        Long currentUserId = userService.findCurrentUserId();
        return jobCycleRepository.findByUserIdAndEndTimeIsNull(currentUserId).orElseThrow(NoSuchElementException::new);
    }


}
