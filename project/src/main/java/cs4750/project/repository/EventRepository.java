package cs4750.project.repository;

import cs4750.project.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByLocation_LocationId(Long locationId);
}
