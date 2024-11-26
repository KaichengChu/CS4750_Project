package cs4750.project.repository;

import cs4750.project.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
