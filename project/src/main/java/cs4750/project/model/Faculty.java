package cs4750.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Faculty")
public class Faculty extends User{
    @Id
    @Column(name = "User_Id")
    private Long userId;

    @Column(name = "Department")
    private String department;

    public Faculty(Long userId, String email, String lastName, String firstName, String department) {
        super(userId, email, lastName, firstName);
        this.department = department;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
