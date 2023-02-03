package pl.kuba.managementapp.JobCycle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCycleRepository extends CrudRepository<JobCycle, Long> {

    JobCycle findJobCycleById(Long id);

}