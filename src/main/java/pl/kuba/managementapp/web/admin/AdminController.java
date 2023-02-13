package pl.kuba.managementapp.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserRepository;
import pl.kuba.managementapp.User.UserRole;
import pl.kuba.managementapp.User.UserService;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;
    private final UserService userService;

    public AdminController(AdminService adminService,
                           UserRepository userRepository, UserService userService) {
        this.adminService = adminService;
        this.userRepository = userRepository;
        this.userService = userService;
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

}
