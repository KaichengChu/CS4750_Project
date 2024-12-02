package cs4750.project.controller;

import cs4750.project.model.Location;
import cs4750.project.repository.LocationRepository;
import cs4750.project.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private EventRepository eventRepository;
    // Get all locations
    @GetMapping
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // Get a location by ID
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return locationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new location
    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        if (locationRepository.existsById(location.getLocationId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location ID already exists!");
        }
        Location savedLocation = locationRepository.save(location);
        return ResponseEntity.status(201).body(savedLocation);
    }

    // Update an existing location
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location updatedLocation) {
        return locationRepository.findById(id)
                .map(existingLocation -> {
                    existingLocation.setLocationName(updatedLocation.getLocationName());
                    existingLocation.setLocationDescription(updatedLocation.getLocationDescription());
                    existingLocation.setLocationCapacity(updatedLocation.getLocationCapacity());
                    Location savedLocation = locationRepository.save(existingLocation);
                    return ResponseEntity.ok(savedLocation);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a location by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) {
        if (!locationRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found!");
        }

        // Check if any event refers to this location
        boolean hasEvents = eventRepository.existsByLocation_LocationId(id);
        if (hasEvents) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot delete location as it is referenced by one or more events.");
        }

        // Proceed with deletion
        locationRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
