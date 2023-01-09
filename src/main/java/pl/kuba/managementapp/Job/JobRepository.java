package pl.kuba.managementapp.Job;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface JobRepository extends CrudRepository<Job, Long>
{

    List<Job> findAll();

}
