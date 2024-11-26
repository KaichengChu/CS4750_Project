package cs4750.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Faculty")
public class Faculty {
    @Id
    @Column(name = "User_Id")
    private Long userId;

    @Column(name = "Department")
    private String department;

    public Faculty(Long userId, String department) {
        this.userId = userId;
        this.department = department;
    }

    public Faculty() {
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
