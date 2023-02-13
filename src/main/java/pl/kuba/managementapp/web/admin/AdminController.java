package pl.kuba.managementapp.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kuba.managementapp.Salary.Salary;
import pl.kuba.managementapp.Salary.SalaryService;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserRole;
import pl.kuba.managementapp.User.UserService;

import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final SalaryService salaryService;
    public AdminController(AdminService adminService,
                           UserService userService, SalaryService salaryService) {
        this.adminService = adminService;
        this.userService = userService;
        this.salaryService = salaryService;
    }

    @GetMapping("/adminsPanel")
    public String adminsPanel(){
        return "adminsPanel";
    }

    @GetMapping("/add")
    String registrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addEmployeeForm";
    }

    @PostMapping("/add")
    String register(User user,
                    @RequestParam String role){
        UserRole properRole = adminService.findRoleByName(role);
        user.getRoles().add(properRole);
        adminService.saveUser(user);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    String registrationConfirmation()
    {
        return "registration-confirmation";
    }

    @GetMapping("/delete")
    String deleteForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "deleteEmployeeForm";
    }

    @PostMapping("/delete")
    String deleteForm(User user) {
        userService.deleteUserByEmail(user.getEmail());
        return "redirect:/confirmation";
    }

    @GetMapping("/showAllEmployees")
    String showEmployees(Model model){
        List<User> employeesList = userService.findAllEmployees();
        model.addAttribute("employeesList", employeesList);
        return "employeeList";
    }

    @GetMapping("/showAllSalaries")
    String showSalaries(Model model){
//        List<User> employeesList = userService.findAllEmployees();
        List<Salary> salaries = salaryService.findAllSalaries();
        model.addAttribute("salariesList", salaries);
        return "employeeSalariesList";
    }

}
