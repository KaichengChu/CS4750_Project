package cs4750.project.repository;

import cs4750.project.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
