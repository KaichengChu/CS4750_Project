package cs4750.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Location")
public class Location {

    @Id
    @Column(name = "Location_ID", nullable = false) // Primary key but not auto-generated
    private Long locationId;

    @Column(name = "Location_Name", nullable = false)
    private String locationName;

    @Column(name = "Location_Description")
    private String locationDescription;

    @Column(name = "Location_Capacity", nullable = false)
    private Integer locationCapacity;

    // Default Constructor
    public Location() {}

    // Constructor with fields
    public Location(Long locationId, String locationName, String locationDescription, Integer locationCapacity) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationCapacity = locationCapacity;
    }

    // Getters and Setters
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public Integer getLocationCapacity() {
        return locationCapacity;
    }

    public void setLocationCapacity(Integer locationCapacity) {
        this.locationCapacity = locationCapacity;
    }
}
