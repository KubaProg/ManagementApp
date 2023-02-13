package pl.kuba.managementapp.Salary;

import org.springframework.stereotype.Service;
import pl.kuba.managementapp.JobResult.JobResult;
import pl.kuba.managementapp.JobResult.JobResultRepository;
import pl.kuba.managementapp.PickResult.PickResult;
import pl.kuba.managementapp.PickResult.PickResultRepository;
import pl.kuba.managementapp.User.UserService;

@Service
public class SalaryService {

    PickResultRepository pickResultRepository;
    JobResultRepository jobResultRepository;
    UserService userService;

    public SalaryService(PickResultRepository pickResultRepository, JobResultRepository jobResultRepository, UserService userService) {
        this.pickResultRepository = pickResultRepository;
        this.jobResultRepository = jobResultRepository;
        this.userService = userService;
    }



}
