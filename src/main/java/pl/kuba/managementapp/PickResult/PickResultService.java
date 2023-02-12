package pl.kuba.managementapp.PickResult;

import org.springframework.stereotype.Service;
import pl.kuba.managementapp.User.User;

import java.text.DecimalFormat;

@Service
public class PickResultService {

    PickResultRepository pickResultRepository;

    public PickResultService(PickResultRepository pickResultRepository) {
        this.pickResultRepository = pickResultRepository;
    }

    public void savePickResult(PickResult pickResult){
        pickResultRepository.save(pickResult);
    }

    public double countMoney(Double weight){
        Double result = (weight*1.5);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String formatted = decimalFormat.format(result);
        return Double.parseDouble(formatted);
    }

    public PickResult createObject(Long id, String fieldName, User user) {
        return new PickResult(id,fieldName,user);
    }

    public PickResult findRecentPickResult(Long currentUserId) {
        return pickResultRepository.findPickResultByUserIdAndWeightIsNull(currentUserId);
    }
}
