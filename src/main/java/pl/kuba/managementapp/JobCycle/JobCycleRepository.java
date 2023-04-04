package pl.kuba.managementapp.JobCycle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobCycleRepository extends CrudRepository<JobCycle, Long> {
    Optional<JobCycle> findByUserIdAndEndTimeIsNull(Long id);

    List<JobCycle> findAllByUserId(Long id);

    List<JobCycle> findAllByUserIdAndJob_IdAndField_Id(Long user_Id, Long job_id,Long field_id);

}
