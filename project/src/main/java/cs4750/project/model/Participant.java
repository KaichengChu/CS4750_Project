package cs4750.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Participant")
public class Participant {
    @Id
    @Column(name = "User_id")
    private Long userId;

    @Column(name = "Total_attended_events", columnDefinition = "INT DEFAULT 0")
    private Integer totalAttendedEvents;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalAttendedEvents() {
        return totalAttendedEvents;
    }

    public void setTotalAttendedEvents(Integer totalAttendedEvents) {
        this.totalAttendedEvents = totalAttendedEvents;
    }
}
