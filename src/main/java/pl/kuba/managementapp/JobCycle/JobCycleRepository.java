package pl.kuba.managementapp.JobCycle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobCycleRepository extends CrudRepository<JobCycle, Long> {

//    JobCycle findJobCycleById(Long id);

     Optional<JobCycle> findByJobId(Long id);

    Optional<JobCycle> findByUserIdAndEndTimeIsNull(Long id);


}
