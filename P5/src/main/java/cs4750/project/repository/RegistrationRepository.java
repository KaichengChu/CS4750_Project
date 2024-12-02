package cs4750.project.repository;

import cs4750.project.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    // Find all registrations for a specific event
    List<Registration> findByEvent_EventId(Long eventId);

    // Find all registrations for a specific user
    List<Registration> findByUser_UserId(Long userId);
}
