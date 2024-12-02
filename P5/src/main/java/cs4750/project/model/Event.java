package cs4750.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Event_ID")
    private Long eventId;

    @Column(name = "Event_Name", nullable = false)
    private String eventName;

    @Column(name = "Event_Type", nullable = false)
    private String eventType;

    @Column(name = "Event_StartTime", nullable = false)
    private LocalDateTime eventStartTime;

    @Column(name = "Event_EndTime", nullable = false)
    private LocalDateTime eventEndTime;

    @Column(name = "Event_Description")
    private String eventDescription;

    @Column(name = "Create_Date", nullable = false)
    private LocalDateTime createDate;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "Organizer_ID", nullable = false)
    private Organizer organizer;

    @ManyToOne
    @JoinColumn(name = "Location_ID", nullable = false)
    private Location location;

    // Default constructor
    public Event() {}

    // Constructor with fields
    public Event(String eventName, String eventType, LocalDateTime eventStartTime, LocalDateTime eventEndTime,
                 String eventDescription, LocalDateTime createDate, Organizer organizer, Location location) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventDescription = eventDescription;
        this.createDate = createDate;
        this.organizer = organizer;
        this.location = location;
    }

    // Getters and Setters
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public LocalDateTime getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(LocalDateTime eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
