package cs4750.project.repository;

import cs4750.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
        boolean existsById(Long userId);
}
