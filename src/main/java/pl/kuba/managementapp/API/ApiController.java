package pl.kuba.managementapp.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kuba.managementapp.JobResult.JobResultDto;
import pl.kuba.managementapp.JobResult.JobResultService;
import pl.kuba.managementapp.PickResult.PickResultDto;
import pl.kuba.managementapp.PickResult.PickResultService;
import pl.kuba.managementapp.Salary.SalaryDto;
import pl.kuba.managementapp.Salary.SalaryService;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserService;
import pl.kuba.managementapp.web.admin.AdminService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final PickResultService pickResultService;
    private final SalaryService salaryService;
    private final JobResultService jobResultService;
    private final AdminService adminService;
    private final UserService userService;

    public ApiController(PickResultService pickResultService, SalaryService salaryService, JobResultService jobResultService, AdminService adminService, UserService userService) {
        this.pickResultService = pickResultService;
        this.salaryService = salaryService;
        this.jobResultService = jobResultService;
        this.adminService = adminService;
        this.userService = userService;
    }


    @GetMapping("/pickResults")
    ResponseEntity<List<PickResultDto>> getAllPickResults()
    {
        if(pickResultService.getAll().isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(pickResultService.getAll());
        }
    }

    @GetMapping("/salaries")
    ResponseEntity<List<SalaryDto>> getAllSalaries(){
        if(salaryService.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(salaryService.findAll());
        }
    }

    @GetMapping("/jobResults")
    ResponseEntity<List<JobResultDto>> getAllJobResults(){
        if(salaryService.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(jobResultService.getAllJobResults());
        }
    }

    @PostMapping("/user")
    @ResponseBody
    ResponseEntity<User> createUser(@RequestBody User user){
        if(userService.findUserByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            User savedUser = adminService.saveUser(user);
            URI savedUserUri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/id")
                    .buildAndExpand(savedUser.getId())
                    .toUri();
            return ResponseEntity.created(savedUserUri).body(savedUser);
        }
    }




}
