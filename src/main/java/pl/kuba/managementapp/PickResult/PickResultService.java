package pl.kuba.managementapp.PickResult;

import org.springframework.stereotype.Service;

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
        return weight*1.5;
    }

}
