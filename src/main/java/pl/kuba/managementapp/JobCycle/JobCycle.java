package pl.kuba.managementapp.JobCycle;
import jakarta.persistence.*;
import pl.kuba.managementapp.Employee.Employee;

import java.util.ArrayList;
import java.util.List;

@Entity
public class JobCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private ArrayList<Employee> employees;
    private String startTime;

    private String endTime;

    private String jobName;

    public JobCycle() {
        // default constructor
    }

    public JobCycle(String startTime, String endTime, String status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.jobName = status;
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

    public void setJobName(String status) {
        this.jobName = status;
    }
}
