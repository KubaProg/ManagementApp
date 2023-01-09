package pl.kuba.managementapp.Job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class JobService {

private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<String> findAllJobs(){
        return jobRepository.findAll().stream().map(Job::getName).toList();
    }

}
