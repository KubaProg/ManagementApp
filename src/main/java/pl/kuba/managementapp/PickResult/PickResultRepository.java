package pl.kuba.managementapp.PickResult;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickResultRepository extends CrudRepository<PickResult, Long> {

    PickResult findFirstPickResultByUserIdAndWeightIsNull(Long id);

    List<PickResult> findAllByUserIdAndFieldName(Long id, String fieldName);
    List<PickResult> findAllByUserId(Long id);

    List<PickResult> findAll();

}
