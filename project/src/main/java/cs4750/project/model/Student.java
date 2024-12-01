package cs4750.project.model;

import jakarta.persistence.*;

@Entity
@Table(name="Student")
public class Student extends User{
    @Column(name = "Student_year")
    private Integer studentYear;

    @Column(name = "Major", length = 20)
    private String major;

    // Constructors
    public Student() {
        super();
    }

    public Student(Long userId, String email, String lastName, String firstName, Integer studentYear, String major) {
        super(userId, email, lastName, firstName);
        this.studentYear = studentYear;
        this.major = major;
    }

    // Getters and Setters

    public Integer getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(Integer studentYear) {
        this.studentYear = studentYear;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
