package pl.kuba.managementapp.Field;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public List<Field> findAllFields(){
        return fieldRepository.findAll();
    }

    public Field findByName(String name){
        return fieldRepository.findByName(name);
    }

}
