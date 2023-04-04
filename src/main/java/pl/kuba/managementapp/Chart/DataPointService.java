package pl.kuba.managementapp.Chart;

import org.springframework.stereotype.Service;
import pl.kuba.managementapp.Field.FieldRepository;
import pl.kuba.managementapp.JobCycle.JobCycleRepository;
import pl.kuba.managementapp.JobResult.JobResultService;
import pl.kuba.managementapp.PickResult.PickResult;
import pl.kuba.managementapp.PickResult.PickResultRepository;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserRepository;
import pl.kuba.managementapp.User.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataPointService {

    private UserRepository userRepository;
    private PickResultRepository pickResultRepository;
    private JobCycleRepository jobCycleRepository;
    private UserRoleRepository userRoleRepository;
    private FieldRepository fieldRepository;

    private static final Long PICKING_FIELD_ID = 2L;


    public DataPointService(UserRepository userRepository, PickResultRepository pickResultRepository,
                            JobCycleRepository jobCycleRepository, UserRoleRepository userRoleRepository,
                            FieldRepository fieldRepository) {
        this.userRepository = userRepository;
        this.pickResultRepository = pickResultRepository;
        this.jobCycleRepository = jobCycleRepository;
        this.userRoleRepository = userRoleRepository;
        this.fieldRepository = fieldRepository;
    }

    public List<DataPoint> createDataPointsList(String fieldName){
        List<DataPoint> dataPoints = new ArrayList<>();
        List<Long> employeesId = userRepository.findAllByRolesContains(userRoleRepository.findByName("USER").orElseThrow()).stream().map(User::getId).toList();


        for (Long id : employeesId) {
            Double totalWeight = pickResultRepository.findAllByUserIdAndFieldName(id, fieldName)
                    .stream()
                    .map(PickResult::getWeight)
                    .reduce(0.0, Double::sum);
            System.out.println(totalWeight);

            Long choosenFieldId = fieldRepository.findByName(fieldName).getId();
            User user = userRepository.findById(id).orElseThrow();

            Double totalPickingTime = jobCycleRepository.findAllByUserIdAndJob_IdAndField_Id(id, PICKING_FIELD_ID,choosenFieldId)
                    .stream()
                    .map(JobResultService::countDuration)
                    .reduce(0.0, Double::sum);

            dataPoints.add(new DataPoint(totalWeight, totalPickingTime, user.getFirst_name(), user.getLast_name(),id));
        }
        return dataPoints;
    }

    // 1. Iterujemy po id wszystkich pracowników z tabeli application_user
// 2. Sumujemy wszystkie wartosci z kolumny weight gdzie user_id == id oraz field_name == nazwa pola do którego akurat liczymy statystyki
// 3. Dla każdego rekordu gdzie user_id = id oraz job_id = 2 liczymy duraion miedzy start i end time i sumujemy to do godzin
// 4. Tworzymy nowy obiekt DataPoint z tych danych i ogolnie danych konkretnego pracownika
// 5. Dodajemy to do listy
// 6. W ChartControllerze wywołujemy metodę zwracającą listę DataPointów
// 7. Wysyłamy listę do scatteredChart
}
