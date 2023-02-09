package pl.kuba.managementapp.Job;

import jakarta.persistence.*;

@Entity
@Table(name = "Job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Job(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Job() {

    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}
