package cs4750.project.controller;

import cs4750.project.model.Organization;
import cs4750.project.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
@CrossOrigin
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    // Get the organization by id
    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable Long id) {
        return organizationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new organization
    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        Organization savedOrganization = organizationRepository.save(organization);
        return ResponseEntity.status(201).body(savedOrganization);
    }

    // Update an existing organization
    @PutMapping("/{id}")
    public ResponseEntity<Organization> updateOrganization(
            @PathVariable Long id, @RequestBody Organization updatedOrganization) {
        return organizationRepository.findById(id)
                .map(existingOrganization -> {
                    existingOrganization.setOrganizationName(updatedOrganization.getOrganizationName());
                    existingOrganization.setOrganizationDescription(updatedOrganization.getOrganizationDescription());
                    Organization savedOrganization = organizationRepository.save(existingOrganization);
                    return ResponseEntity.ok(savedOrganization);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an organization by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        if (organizationRepository.existsById(id)) {
            organizationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
