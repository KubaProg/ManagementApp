package pl.kuba.managementapp.Salary;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.kuba.managementapp.JobResult.JobResult;
import pl.kuba.managementapp.JobResult.JobResultRepository;
import pl.kuba.managementapp.PickResult.PickResult;
import pl.kuba.managementapp.PickResult.PickResultRepository;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    private final PickResultRepository pickResultRepository;
    private final JobResultRepository jobResultRepository;
    private final UserService userService;
    private final SalaryRepository salaryRepository;
    private final SalaryDtoMapper salaryDtoMapper;

    public SalaryService(PickResultRepository pickResultRepository, JobResultRepository jobResultRepository, UserService userService,
                         SalaryRepository salaryRepository, SalaryDtoMapper salaryDtoMapper) {
        this.pickResultRepository = pickResultRepository;
        this.jobResultRepository = jobResultRepository;
        this.userService = userService;
        this.salaryRepository = salaryRepository;
        this.salaryDtoMapper = salaryDtoMapper;
    }

    @Transactional
    public void increaseSalary(){
        Long id = userService.findCurrentUserId();
        User user = userService.findCurrentUser();
        List<JobResult> jobResults = jobResultRepository.findAllByUserId(id);
        List<PickResult> pickResults = pickResultRepository.findAllByUserId(id);
        Double jobResultsMoney = jobResults.stream()
                .map(JobResult::getMoney)
                .reduce(0.0, Double::sum);
        Double pickResultsMoney = pickResults.stream()
                .mapToDouble(pickResult -> pickResult.getMoney() == null ? 0.0 : pickResult.getMoney())
                .reduce(Double::sum)
                .orElse(0.0);
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
                .map(salaryDtoMapper::map)
                .toList();
    }

    public List<SalaryDto> findAllSalaries(){
        return salaryRepository.findAll()
                .stream()
                .map(salaryDtoMapper::map)
                .toList();
    }

    public Optional<SalaryDto> getSalaryById(Long id) {
        return salaryRepository.findById(id).map(salaryDtoMapper::map);
    }

    public void updateSalary(SalaryDto salaryDto) {
        Salary salary = salaryDtoMapper.map(salaryDto);
        salaryRepository.save(salary);
    }
}
