package cs4750.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="[User]")
public class User {
    @Id
    @Column(name = "User_id")
    private Long userId;

    @Column(name = "Email")
    private String email;

    @Column(name = "Last_name")
    private String lastName;

    @Column(name = "First_name")
    private String firstName;

    public User(Long userId, String email, String lastName, String firstName) {
        this.userId = userId;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
