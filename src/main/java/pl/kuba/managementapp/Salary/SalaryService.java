package pl.kuba.managementapp.Salary;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.kuba.managementapp.JobResult.JobResult;
import pl.kuba.managementapp.JobResult.JobResultRepository;
import pl.kuba.managementapp.PickResult.PickResult;
import pl.kuba.managementapp.PickResult.PickResultRepository;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryService {

    PickResultRepository pickResultRepository;
    JobResultRepository jobResultRepository;
    UserService userService;
    private final SalaryRepository salaryRepository;

    public SalaryService(PickResultRepository pickResultRepository, JobResultRepository jobResultRepository, UserService userService,
                         SalaryRepository salaryRepository) {
        this.pickResultRepository = pickResultRepository;
        this.jobResultRepository = jobResultRepository;
        this.userService = userService;
        this.salaryRepository = salaryRepository;
    }

    @Transactional
    public void updateSalary(){
        Long id = userService.findCurrentUserId();
        User user = userService.findCurrentUser();
        List<JobResult> jobResults = jobResultRepository.findAllByUserId(id);
        List<PickResult> pickResults = pickResultRepository.findAllByUserId(id);
        Double jobResultsMoney = jobResults.stream()
                .map(JobResult::getMoney)
                .reduce(0.0, Double::sum);
        Double pickResultsMoney = pickResults.stream()
                .map(PickResult::getMoney)
                .reduce(0.0, Double::sum);
        Double sum = jobResultsMoney+pickResultsMoney;

        Salary salary = salaryRepository.findByUserId(id).orElse(null);
        if (salary == null) {
            salary = new Salary();
            salary.setUser(user);
        }
        salary.setSalary_value(sum);
        salaryRepository.save(salary);
    }

    public List<SalaryDto> findAll(){
        return salaryRepository.findAll().stream()
                .map(SalaryDtoMapper::map)
                .toList();
    }

    public List<Salary> findAllSalaries(){
        return salaryRepository.findAll();
    }

}
