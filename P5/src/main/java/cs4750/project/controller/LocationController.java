package cs4750.project.controller;

import cs4750.project.model.Location;
import cs4750.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        if (locationRepository.existsById(location.getLocationId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already Exist!");
        }


        Location savedLocation = locationRepository.save(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocation);
    }

    @GetMapping
    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<?> getLocation(@PathVariable Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);

        if (location == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(location);
    }

    // Update an existing Location
    @PutMapping("/{locationId}")
    public ResponseEntity<?> updateLocation(@PathVariable Long locationId, @RequestBody Location location) {
        Location existingLocation = locationRepository.findById(locationId).orElse(null);

        if (existingLocation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found");
        }
        existingLocation.setLocationName(location.getLocationName());
        existingLocation.setLocationDescription(location.getLocationDescription());
        existingLocation.setCapacity(location.getCapacity());

        locationRepository.save(existingLocation);
        return ResponseEntity.status(HttpStatus.OK).body(existingLocation);
    }


    @DeleteMapping("/{locationId}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);

        if (location == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found");
        }

        locationRepository.delete(location);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Location deleted successfully");
    }
}
