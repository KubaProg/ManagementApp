package pl.kuba.managementapp.JobCycle;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import pl.kuba.managementapp.Field.Field;
import pl.kuba.managementapp.Job.Job;
import pl.kuba.managementapp.User.User;

@Entity
@Component
@Table(name = "job_cycle")
public class JobCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String startTime;
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    private Field field;

    public JobCycle(Long id, String startTime, String endTime, Job job, User user, Field field) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.job = job;
        this.user = user;
        this.field = field;
    }


    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public JobCycle() {

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
}
