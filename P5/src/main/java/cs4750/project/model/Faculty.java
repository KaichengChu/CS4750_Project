package cs4750.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Faculty")
public class Faculty extends User{
    @Column(name = "Department")
    private String department;

    public Faculty() {
        super(); // Call the no-arg constructor of the superclass (User)
    }

    public Faculty(Long userId, String email, String lastName, String firstName, String department) {
        super(userId, email, lastName, firstName);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
