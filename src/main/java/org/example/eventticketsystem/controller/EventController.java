package org.example.eventticketsystem.controller;

import jakarta.validation.Valid;
import org.example.eventticketsystem.model.ApiResponse;
import org.example.eventticketsystem.model.Event;
import org.example.eventticketsystem.model.Request.RequestAttendee;
import org.example.eventticketsystem.model.Request.RequestEvent;
import org.example.eventticketsystem.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping
    public ResponseEntity<?> getAllVenues() {

        List<Event> attendeeList = eventService.getAllEvent();
        System.out.println(attendeeList);
        ApiResponse<List<Event>> apiResponse = ApiResponse.<List<Event>>builder().code(200).message("Get All Attendees").status(HttpStatus.OK).payload(attendeeList).build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getVenueById(@PathVariable Integer id) {
        Event attendee = this.eventService.getEventById(id);
        ApiResponse<Event> response = ApiResponse.<Event>builder().code(200).message("Get Attendee Successful").status(HttpStatus.OK).payload(attendee).build();
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendeeById(@PathVariable Integer id) {
        Event Attendee = this.eventService.deleteEventById(id);
        ApiResponse<Event> response = ApiResponse.<Event>builder().status(HttpStatus.OK).message("Delete Attendee Successful").payload(Attendee).code(200).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> insertNewAttendee(@Valid @RequestBody RequestEvent event) {

        Event event1 = this.eventService.InsertNewEvent(event);
        System.out.println(event1);
        ApiResponse<Event> response = ApiResponse.<Event>builder().status(HttpStatus.CREATED).message("Insert Successful").payload(event1).code(200).build();
        return ResponseEntity.ok(response);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateAttendeeById(@PathVariable Integer id, @Valid @RequestBody RequestEvent event) {
        Event attendee1 = this.eventService.UpdateEventById(event, id);
        ApiResponse<Event> response = ApiResponse.<Event>builder().status(HttpStatus.OK).message("Update Attendee Successful").payload(attendee1).code(200).build();
        return ResponseEntity.ok(response);


    }
}
