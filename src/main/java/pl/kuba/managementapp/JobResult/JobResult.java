package pl.kuba.managementapp.JobResult;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import pl.kuba.managementapp.User.User;

@Entity
@Component
@Table(name = "job_result")
public class JobResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobName;
    private String fieldName;
    private Double hours;
    private Double money;
    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
