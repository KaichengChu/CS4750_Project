package cs4750.project.controller;

import cs4750.project.model.Organizer;
import cs4750.project.model.Organization;
import cs4750.project.model.User;
import cs4750.project.repository.OrganizerRepository;
import cs4750.project.repository.OrganizationRepository;
import cs4750.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/organizer")
@CrossOrigin
public class OrganizerController {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    // Get all organizers
    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    // Get an organizer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Organizer> getOrganizerById(@PathVariable Long id) {
        return organizerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new organizer
    @PostMapping
    public ResponseEntity<?> addOrganizer(@RequestBody Organizer organizer) {
        // Validate that the User exists
        Optional<User> user = userRepository.findById(organizer.getUser().getUserId());
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body("User does not exist!");
        }

        // Validate that the Organization exists
        Optional<Organization> organization = organizationRepository.findById(organizer.getOrganization().getOrganizationId());
        if (organization.isEmpty()) {
            return ResponseEntity.status(404).body("Organization does not exist!");
        }

        // Save the organizer
        organizer.setUser(user.get());
        organizer.setOrganization(organization.get());
        Organizer savedOrganizer = organizerRepository.save(organizer);

        return ResponseEntity.status(201).body(savedOrganizer);
    }

    // Update an organizer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrganizer(@PathVariable Long id, @RequestBody Organizer updatedOrganizer) {
        return organizerRepository.findById(id)
                .map(existingOrganizer -> {
                    Optional<User> user = userRepository.findById(updatedOrganizer.getUser().getUserId());
                    if (user.isEmpty()) {
                        return ResponseEntity.status(404).body("User does not exist!");
                    }

                    Optional<Organization> organization = organizationRepository.findById(updatedOrganizer.getOrganization().getOrganizationId());
                    if (organization.isEmpty()) {
                        return ResponseEntity.status(404).body("Organization does not exist!");
                    }

                    // Update fields
                    existingOrganizer.setUser(user.get());
                    existingOrganizer.setOrganization(organization.get());
                    existingOrganizer.setRole(updatedOrganizer.getRole());

                    Organizer savedOrganizer = organizerRepository.save(existingOrganizer);
                    return ResponseEntity.ok(savedOrganizer);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an organizer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizer(@PathVariable Long id) {
        if (organizerRepository.existsById(id)) {
            organizerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
