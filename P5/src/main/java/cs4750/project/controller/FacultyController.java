package cs4750.project.controller;

import cs4750.project.model.Faculty;
import cs4750.project.model.Student;
import cs4750.project.repository.FacultyRepository;
import cs4750.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping
    public List<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id){
        return facultyRepository.findById(id)
                .map(faculty -> ResponseEntity.ok().body(faculty))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addFaculty(@RequestBody Faculty faculty) {
        if (facultyRepository.existsById(faculty.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Faculty already exists!");
        }

        Faculty savedFaculty = facultyRepository.save(faculty);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFaculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty updatedFaculty){
        return facultyRepository.findById(id).map(faculty -> {
            faculty.setDepartment(updatedFaculty.getDepartment());
            return ResponseEntity.ok(facultyRepository.save(faculty));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacultyById(@PathVariable Long id){
        if(facultyRepository.existsById(id)){
            facultyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
