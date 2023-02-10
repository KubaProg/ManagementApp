package pl.kuba.managementapp.PickResult;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickResultRepository extends CrudRepository<PickResult, Long> {

PickResult findPickResultByIdAndWeightIsNull(Long id);

}
