package cs4750.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Organizer")
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key referencing User
    private User user;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false) // Foreign key referencing Organization
    private Organization organization;

    @Column(name = "role", nullable = false)
    private String role;

    // Default constructor
    public Organizer() {}

    public Organizer(User user, Organization organization, String role) {
        this.user = user;
        this.organization = organization;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
