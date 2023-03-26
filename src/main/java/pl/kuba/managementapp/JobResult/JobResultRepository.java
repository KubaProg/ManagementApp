package pl.kuba.managementapp.JobResult;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobResultRepository extends CrudRepository<JobResult, Long> {

    List<JobResult> findAllByUserId(Long id);

    JobResult findFirstJobResultByUserIdAndHoursIsNull(Long currentUserId);

    List<JobResult> findAll();
}
