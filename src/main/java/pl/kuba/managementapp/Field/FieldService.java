package pl.kuba.managementapp.Field;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public List<String> findAllFieldNames(){
        return fieldRepository.findAll()
                .stream()
                .map(Field::getName)
                .toList();
    }

}
