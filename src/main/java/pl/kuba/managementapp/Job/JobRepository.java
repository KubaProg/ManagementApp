package pl.kuba.managementapp.Job;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Long>
{

    List<Job> findAll();

    Job findByName(String name);

}
