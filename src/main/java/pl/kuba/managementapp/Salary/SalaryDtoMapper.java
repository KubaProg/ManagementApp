package pl.kuba.managementapp.Salary;

public class SalaryDtoMapper {

    static SalaryDto map(Salary salary){
        SalaryDto salaryDto = new SalaryDto();
        salaryDto.setId(salary.getId());
        salaryDto.setValue(salary.getSalary_value());
        salaryDto.setUser_id(salary.getUser().getId());
        salaryDto.setFirst_name(salary.getUser().getFirst_name());
        salaryDto.setLast_name(salary.getUser().getLast_name());
        return salaryDto;
    }

}
