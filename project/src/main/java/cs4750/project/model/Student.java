package cs4750.project.model;

import jakarta.persistence.*;

@Entity
@Table(name="Student")
public class Student {
    @Id
    @Column(name = "User_id")
    private Long userId;

    @Column(name = "Student_year")
    private Integer studentYear;

    @Column(name = "Major", length = 10)
    private String major;

    // Constructors
    public Student() {}

    public Student(Integer studentYear, String major) {
        this.studentYear = studentYear;
        this.major = major;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
