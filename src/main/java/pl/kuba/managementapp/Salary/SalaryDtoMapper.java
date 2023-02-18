package pl.kuba.managementapp.Salary;

import org.springframework.stereotype.Service;
import pl.kuba.managementapp.User.UserService;

@Service
public class SalaryDtoMapper {

    UserService userService;

    public SalaryDtoMapper(UserService userService) {
        this.userService = userService;
    }

     SalaryDto map(Salary salary){
        SalaryDto salaryDto = new SalaryDto();
        salaryDto.setId(salary.getId());
        salaryDto.setValue(salary.getSalary_value());
        salaryDto.setUser_id(salary.getUser().getId());
        salaryDto.setFirst_name(salary.getUser().getFirst_name());
        salaryDto.setLast_name(salary.getUser().getLast_name());
        return salaryDto;
    }

     Salary map(SalaryDto salaryDto){
        Salary salary = new Salary();
        salary.setId(salaryDto.getId());
        salary.setSalary_value(salaryDto.getValue());
        salary.setUser(userService.getUserById(salaryDto.getUser_id()));
        return salary;
    }


}
