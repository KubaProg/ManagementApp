package pl.kuba.managementapp.Salary;

import jakarta.persistence.*;
import pl.kuba.managementapp.User.User;

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double salary_value;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Salary() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary_value() {
        return salary_value;
    }

    public void setSalary_value(Double salary_value) {
        this.salary_value = salary_value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
