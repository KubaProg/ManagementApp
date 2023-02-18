package pl.kuba.managementapp.JobResult;

import org.springframework.stereotype.Service;
import pl.kuba.managementapp.JobCycle.JobCycle;
import pl.kuba.managementapp.JobCycle.JobCycleRepository;
import pl.kuba.managementapp.JobCycle.JobCycleService;
import pl.kuba.managementapp.PickResult.PickResult;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserService;

import java.text.DecimalFormat;
import java.text.Format;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Service
public class JobResultService {

    JobResultRepository jobResultRepository;
    JobCycleRepository jobCycleRepository;
    JobCycleService jobCycleService;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public JobResultService(JobResultRepository jobResultRepository, JobCycleRepository jobCycleRepository,  JobCycleService jobCycleService) {
        this.jobResultRepository = jobResultRepository;
        this.jobCycleRepository = jobCycleRepository;
        this.jobCycleService = jobCycleService;
    }
    public JobResult createObject(Long id, String jobName, String fieldName, User user) {
        return new JobResult(id,jobName,fieldName,user);
    }

    public void saveJobResult(JobResult jobResult){
        jobResultRepository.save(jobResult);
    }

    public JobResult findRecentJobResult(Long currentUserId) {
        return jobResultRepository.findJobResultByUserIdAndHoursIsNull(currentUserId);
    }

    public JobResult countTimeAndMoney(JobResult jobResult, Long id){
        JobCycle jobCycle = jobCycleService.findLastJobCycleById(id);
        Double duration = countDuration(jobCycle);
        jobResult.setHours(duration);
        jobResult.setMoney(countMoney(duration));
        return jobResult;
    }

    public Double countDuration(JobCycle jobCycle){
        String startTime = jobCycle.getStartTime();
        String endTime = jobCycle.getEndTime();
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        Duration duration = Duration.between(start, end);
        long minutes = duration.toMinutes();
        String formatted = decimalFormat.format(Double.parseDouble(String.valueOf(minutes)));
        return Double.parseDouble(formatted)/60;
    }

    public Double countMoney(Double hours){
        String formatted = decimalFormat.format(hours * 11.5);
        return Double.parseDouble(formatted);
    }

    public List<JobResultDto> getAllJobResults(){
        return jobResultRepository.findAll()
                .stream()
                .map(JobResultDtoMapper::map)
                .toList();
    }


}
