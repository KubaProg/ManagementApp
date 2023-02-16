package pl.kuba.managementapp.API;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kuba.managementapp.PickResult.PickResult;
import pl.kuba.managementapp.PickResult.PickResultDto;
import pl.kuba.managementapp.PickResult.PickResultService;

import java.util.List;

@RestController
@RequestMapping("/pickResults")
public class ApiController {

    private final PickResultService pickResultService;

    public ApiController(PickResultService pickResultService) {
        this.pickResultService = pickResultService;
    }


    @GetMapping
    ResponseEntity<List<PickResultDto>> getAllPickResults()
    {
        if(pickResultService.getAll().isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(pickResultService.getAll());
        }
    }



}
