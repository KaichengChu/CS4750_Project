package cs4750.project.controller;

import cs4750.project.model.Location;
import cs4750.project.repository.LocationRepository;
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
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
