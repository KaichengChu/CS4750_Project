package cs4750.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Organization_Table")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Organization_ID")
    private Long organizationId;

    @Column(name = "Organization_Name", nullable = false)
    private String organizationName;

    @Column(name = "Organization_Description")
    private String organizationDescription;

    //Constructors
    public Organization() {}

    public Organization(String organizationName, String organizationDescription) {
        this.organizationName = organizationName;
        this.organizationDescription = organizationDescription;
    }

    // Getters and Setters
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }
}
