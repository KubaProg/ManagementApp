package pl.kuba.managementapp.JobCycle;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.kuba.managementapp.Field.Field;
import pl.kuba.managementapp.Job.Job;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Transactional
    public void saveJobCycle(JobCycle jobCycle){
        jobCycleRepository.save(jobCycle);
    }

    public JobCycle findRecentlyStartedJobCycle(){
        Long currentUserId = userService.findCurrentUserId();
        return jobCycleRepository.findByUserIdAndEndTimeIsNull(currentUserId).orElseThrow(NoSuchElementException::new);
    }

    public JobCycle createObject(Long id, String startTime, Job job, User user, Field field) {
        return new JobCycle(id,startTime,job,user,field);
    }

    public JobCycle findLastJobCycleById(Long id){
        List<JobCycle> all = jobCycleRepository.findAllByUserId(id);
        List<Long> list = all.stream()
                .map(JobCycle::getId)
                .toList();
        Long properId = Collections.max(list);
        Optional<JobCycle> properJobCycle = jobCycleRepository.findById(properId);
        return properJobCycle.orElseThrow(NoSuchElementException::new);
    }

}
