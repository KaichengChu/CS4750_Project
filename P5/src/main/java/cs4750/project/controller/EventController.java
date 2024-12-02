package cs4750.project.controller;

import cs4750.project.model.Event;
import cs4750.project.model.Organizer;
import cs4750.project.model.Location;
import cs4750.project.repository.EventRepository;
import cs4750.project.repository.OrganizerRepository;
import cs4750.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private LocationRepository locationRepository;

    // Get all events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get an event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new event
    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        // Validate Organizer
        Optional<Organizer> organizer = organizerRepository.findById(event.getOrganizer().getId());
        if (organizer.isEmpty()) {
            return ResponseEntity.status(404).body("Organizer not found!");
        }

        // Validate Location
        Optional<Location> location = locationRepository.findById(event.getLocation().getLocationId());
        if (location.isEmpty()) {
            return ResponseEntity.status(404).body("Location not found!");
        }

        event.setOrganizer(organizer.get());
        event.setLocation(location.get());
        event.setCreateDate(LocalDateTime.now());

        Event savedEvent = eventRepository.save(event);
        return ResponseEntity.status(201).body(savedEvent);
    }

    // Update an existing event
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    // Update basic event information
                    existingEvent.setEventName(updatedEvent.getEventName());
                    existingEvent.setEventType(updatedEvent.getEventType());
                    existingEvent.setEventStartTime(updatedEvent.getEventStartTime());
                    existingEvent.setEventEndTime(updatedEvent.getEventEndTime());
                    existingEvent.setEventDescription(updatedEvent.getEventDescription());

                    // Validate and update the organizer
                    if (updatedEvent.getOrganizer() != null) {
                        Optional<Organizer> organizer = organizerRepository.findById(updatedEvent.getOrganizer().getId());
                        if (organizer.isEmpty()) {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Organizer not found!");
                        }
                        existingEvent.setOrganizer(organizer.get());
                    }

                    // Validate and update the location
                    if (updatedEvent.getLocation() != null) {
                        Optional<Location> location = locationRepository.findById(updatedEvent.getLocation().getLocationId());
                        if (location.isEmpty()) {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found!");
                        }
                        existingEvent.setLocation(location.get());
                    }

                    // Save the updated event
                    Event savedEvent = eventRepository.save(existingEvent);
                    return ResponseEntity.ok(savedEvent);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // Delete an event by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
