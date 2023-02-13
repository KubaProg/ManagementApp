package pl.kuba.managementapp.Salary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Long>
{

Optional<Salary> findByUserId(Long id);

List<Salary> findAll();

}
