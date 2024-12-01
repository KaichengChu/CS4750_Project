package cs4750.project.controller;

import cs4750.project.model.Participant;
import cs4750.project.repository.ParticipantRepository;
import cs4750.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/participant")
public class ParticipantController {
    @Autowired
    private ParticipantRepository participantRepository;
    private UserRepository userRepository;

    // Create a new Participant
    @PostMapping
    public ResponseEntity<?> createParticipant(@RequestBody Participant participant) {

        if (!userRepository.existsById(participant.getUserId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No corresponding userId");
        }

        if (participantRepository.existsById(participant.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Participant with this User ID already exists");
        }

        Participant savedParticipant = participantRepository.save(participant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParticipant);
    }

    // Get a Participant by userId
    @GetMapping("/{userId}")
    public ResponseEntity<?> getParticipant(@PathVariable Long userId) {
        Participant participant = participantRepository.findById(userId).orElse(null);

        if (participant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(participant);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateTotalAttendedEvents(@PathVariable Long userId, @RequestBody Integer totalAttendedEvents) {

        Participant participant = participantRepository.findById(userId).orElse(null);

        if (participant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found");
        }

        //participant.setTotalAttendedEvents(totalAttendedEvents);
        participantRepository.save(participant);

        return ResponseEntity.status(HttpStatus.OK).body(participant);
    }

    @DeleteMapping("/userId")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long userId){
        if (participantRepository.existsById(userId)){
            participantRepository.deleteById(userId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
