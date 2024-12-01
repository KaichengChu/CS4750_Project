package cs4750.project.controller;

import cs4750.project.model.Registration;
import cs4750.project.model.Event;
import cs4750.project.model.User;
import cs4750.project.repository.RegistrationRepository;
import cs4750.project.repository.EventRepository;
import cs4750.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registration")
@CrossOrigin
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all registrations
    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    // Get registrations by event ID
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Registration>> getRegistrationsByEvent(@PathVariable Long eventId) {
        List<Registration> registrations = registrationRepository.findByEvent_EventId(eventId);
        return ResponseEntity.ok(registrations);
    }

    // Get registrations by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Registration>> getRegistrationsByUser(@PathVariable Long userId) {
        List<Registration> registrations = registrationRepository.findByUser_UserId(userId);
        return ResponseEntity.ok(registrations);
    }

    // Create a new registration
    @PostMapping
    public ResponseEntity<?> createRegistration(@RequestBody Registration registration) {
        Optional<Event> event = eventRepository.findById(registration.getEvent().getEventId());
        Optional<User> user = userRepository.findById(registration.getUser().getUserId());

        if (event.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found!");
        }
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        registration.setEvent(event.get());
        registration.setUser(user.get());

        Registration savedRegistration = registrationRepository.save(registration);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRegistration);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegistrationStatus(@PathVariable Long id, @RequestBody String statusJson) {
        Optional<Registration> registrationOptional = registrationRepository.findById(id);

        if (registrationOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registration not found!");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String status = objectMapper.readTree(statusJson).get("status").asText();

            Registration existingRegistration = registrationOptional.get();
            existingRegistration.setStatus(status);
            Registration updatedRegistration = registrationRepository.save(existingRegistration);

            return ResponseEntity.ok(updatedRegistration);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format!");
        }
    }






    // Delete a registration
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        if (registrationRepository.existsById(id)) {
            registrationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
