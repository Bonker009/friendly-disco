package org.example.eventticketsystem.controller;

import jakarta.validation.Valid;
import org.example.eventticketsystem.model.ApiResponse;
import org.example.eventticketsystem.model.Attendee;
import org.example.eventticketsystem.model.Request.RequestAttendee;
import org.example.eventticketsystem.model.Request.RequestVenue;
import org.example.eventticketsystem.model.Venue;
import org.example.eventticketsystem.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;


    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }


    @GetMapping
    public ResponseEntity<?> getAllVenues() {

        List<Attendee> attendeeList = attendeeService.getAllAttendees();
        System.out.println(attendeeList);
        ApiResponse<List<Attendee>> apiResponse = ApiResponse.<List<Attendee>>builder().code(200).message("Get All Attendees").status(HttpStatus.OK).payload(attendeeList).build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getVenueById(@PathVariable Integer id) {
        Attendee attendee = this.attendeeService.getAttendeeById(id);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder().code(200).message("Get Attendee Successful").status(HttpStatus.OK).payload(attendee).build();
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendeeById(@PathVariable Integer id) {
        Attendee Attendee = attendeeService.deleteAttendeeById(id);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder().status(HttpStatus.OK).message("Delete Attendee Successful").payload(Attendee).code(200).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> insertNewAttendee(@Valid @RequestBody RequestAttendee attendee) {
        System.out.println(attendee);
        Attendee attendee1 = attendeeService.InsertNewAttendee(attendee);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder().status(HttpStatus.CREATED).message("Insert Successful").payload(attendee1).code(200).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAttendeeById(@PathVariable Integer id,@Valid @RequestBody RequestAttendee attendee) {
        Attendee attendee1 = this.attendeeService.UpdateAttendeeById(attendee,id);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder().status(HttpStatus.OK).message("Update Attendee Successful").payload(attendee1).code(200).build();
        return ResponseEntity.ok(response);


    }
}
