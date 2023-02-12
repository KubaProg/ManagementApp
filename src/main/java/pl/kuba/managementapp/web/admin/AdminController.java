package pl.kuba.managementapp.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserRole;

@Controller
public class AdminController {

    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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

}
