package pl.kuba.managementapp.Field;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FieldRepository extends CrudRepository<Field,Long> {

    List<Field> findAll();

}
