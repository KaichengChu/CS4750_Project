package cs4750.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_present")
    private Boolean isPresent;

    public Participant() {}

    public Participant(User user, Boolean isPresent) {
        this.user = user;
        this.isPresent = isPresent;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return user.getUserId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Boolean isPresent) {
        this.isPresent = isPresent;
    }
}

