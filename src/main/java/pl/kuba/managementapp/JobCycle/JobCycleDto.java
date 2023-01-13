package pl.kuba.managementapp.JobCycle;

public class JobCycleDto {
    private Long id;

    private String startTime;

    private String endTime;

    private String jobName;

    private Long employee_id;

    private Long field_id;

    public JobCycleDto(Long id, String startTime, String endTime, String jobName, Long employee_id, Long field_id) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.jobName = jobName;
        this.employee_id = employee_id;
        this.field_id = field_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public Long getField_id() {
        return field_id;
    }

    public void setField_id(Long field_id) {
        this.field_id = field_id;
    }

}
