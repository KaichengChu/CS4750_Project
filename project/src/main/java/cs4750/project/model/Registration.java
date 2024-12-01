package cs4750.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // Dummy ID

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event; // Foreign key to Event

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Foreign key to User

    @Column(name = "status", nullable = false)
    private String status; // Registration status (registered, attended, absent)

    // Default constructor
    public Registration() {}

    // Constructor with fields
    public Registration(Event event, User user, String status) {
        this.event = event;
        this.user = user;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
