package pl.kuba.managementapp.JobResult;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobResultRepository extends CrudRepository<JobResult, Long> {


    JobResult findJobResultByUserIdAndHoursIsNull(Long currentUserId);
}
