package pl.kuba.managementapp.JobCycle;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class JobCycleService {

    JobCycleRepository jobCycleRepository;

    public JobCycleService(JobCycleRepository jobCycleRepository) {
        this.jobCycleRepository = jobCycleRepository;
    }

    public String getTime(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = currentTime.format(formatter);
        return timeString;
    }


}
