package pl.kuba.managementapp.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
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
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final PickResultService pickResultService;
    private final SalaryService salaryService;
    private final JobResultService jobResultService;
    private final AdminService adminService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public ApiController(PickResultService pickResultService, SalaryService salaryService, JobResultService jobResultService, AdminService adminService, UserService userService, ObjectMapper objectMapper) {
        this.pickResultService = pickResultService;
        this.salaryService = salaryService;
        this.jobResultService = jobResultService;
        this.adminService = adminService;
        this.userService = userService;
        this.objectMapper = objectMapper;
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


    @DeleteMapping("user/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/salary/{id}")
    ResponseEntity<?> updateSalary(@PathVariable Long id, @RequestBody JsonMergePatch patch){
        try {
            SalaryDto salary = salaryService.getSalaryById(id).orElseThrow();
            SalaryDto salaryPatched = applyPatch(salary, patch);
            salaryService.updateSalary(salaryPatched);
        }catch(NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    private SalaryDto applyPatch(SalaryDto salary, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode salaryNode = objectMapper.valueToTree(salary);
        JsonNode salaryPatchedNode = patch.apply(salaryNode);
        return objectMapper.treeToValue(salaryPatchedNode, SalaryDto.class);
    }




}
