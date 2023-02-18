package pl.kuba.managementapp.JobResult;

public class JobResultDtoMapper {

    static JobResultDto map(JobResult jobResult){
        JobResultDto jobResultDto = new JobResultDto();
        jobResultDto.setId(jobResult.getId());
        jobResultDto.setJobName(jobResult.getJobName());
        jobResultDto.setFieldName(jobResult.getFieldName());
        jobResultDto.setHours(jobResult.getHours());
        jobResultDto.setMoney(jobResult.getMoney());
        jobResultDto.setUser_id(jobResult.getUser().getId());
        jobResultDto.setFirst_name(jobResult.getUser().getFirst_name());
        jobResultDto.setLast_name(jobResult.getUser().getLast_name());
        return jobResultDto;
    }

}
