package pl.kuba.managementapp.Employee;

import jakarta.persistence.*;
import pl.kuba.managementapp.JobCycle.JobCycle;

import java.util.ArrayList;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToMany(mappedBy = "employees")
    private ArrayList<JobCycle> jobCycles;

    public Employee() {
        // default constructor
    }

    public Employee(Long id, String name, String email, ArrayList<JobCycle> jobCycle) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobCycles = jobCycle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<JobCycle> getJobCycle() {
        return jobCycles;
    }

    public void setJobCycle(ArrayList<JobCycle> jobCycle) {
        this.jobCycles = jobCycle;
    }
}