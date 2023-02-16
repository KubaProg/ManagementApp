package pl.kuba.managementapp.PickResult;

public class PickResultDtoMapper {

    static PickResultDto map(PickResult pickResult){
        PickResultDto dto = new PickResultDto();
        dto.setId(pickResult.getId());
        dto.setWeight(pickResult.getWeight());
        dto.setMoney(pickResult.getMoney());
        dto.setField_name(pickResult.getFieldName());
        dto.setUserId(pickResult.getUser().getId());
        dto.setFirst_name(pickResult.getUser().getFirst_name());
        dto.setLast_name(pickResult.getUser().getLast_name());
        return dto;
    }

}
