package pl.kuba.managementapp.PickResult;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickResultRepository extends CrudRepository<PickResult, Long> {

    PickResult findPickResultByUserIdAndWeightIsNull(Long id);

    List<PickResult> findAllByUserId(Long id);

}
