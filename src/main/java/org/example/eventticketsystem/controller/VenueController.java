package org.example.eventticketsystem.controller;

import jakarta.validation.Valid;
import org.example.eventticketsystem.model.ApiResponse;
import org.example.eventticketsystem.model.Request.RequestVenue;
import org.example.eventticketsystem.model.Venue;
import org.example.eventticketsystem.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }


    @GetMapping
    public ResponseEntity<?> getAllVenues() {

            List<Venue> venueList = venueService.getAllVenues();
            System.out.println(venueList);
            ApiResponse<List<Venue>> apiResponse = ApiResponse.<List<Venue>>builder().code(200).message("Get All Venues").status(HttpStatus.OK).payload(venueList).build();
            return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getVenueById(@PathVariable Integer id) {
            Venue venue = venueService.getVenueById(id);
            ApiResponse<Venue> response = ApiResponse.<Venue>builder().code(200).message("Get Venue Successful").status(HttpStatus.OK).payload(venue).build();
            return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenueById(@PathVariable Integer id) {
        Venue venue = venueService.deleteVenueById(id);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder().status(HttpStatus.OK).message("Delete Successful").payload(venue).code(200).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> insertNewVenue(@Valid @RequestBody RequestVenue venue) {
        Venue venue1 = venueService.InsertNewVenue(venue);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder().status(HttpStatus.CREATED).message("Insert Successful").payload(venue1).code(200).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateVenueById(@PathVariable Integer id, @RequestBody RequestVenue venue) {
        Venue venue1 = venueService.UpdateVenueById(venue, id);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder().status(HttpStatus.OK).message("Update Successful").payload(venue1).code(200).build();
        return ResponseEntity.ok(response);
    }

}
