package org.example.eventticketsystem.service.serviceImpl;

import org.example.eventticketsystem.exception.CustomNotFoundException;
import org.example.eventticketsystem.model.Request.RequestVenue;
import org.example.eventticketsystem.model.Venue;
import org.example.eventticketsystem.repository.VenueRepository;
import org.example.eventticketsystem.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenues() {
        return this.venueRepository.getAllVenues();

    }

    @Override
    public Venue getVenueById(Integer id) {
        Venue venue = venueRepository.getVenueById(id);
        if (venue == null) {
            throw new CustomNotFoundException("Venue with " + id + " does not exist!");
        }
        return venue;
    }

    @Override
    public Venue UpdateVenueById(RequestVenue venue, Integer id) {
        Venue venue1 = venueRepository.getVenueById(id);
        if (venue1 == null) {
            throw new CustomNotFoundException("Venue with " + id + " does not exist!");
        }
        return this.venueRepository.updateVenueById(venue, id);
    }

    @Override
    public Venue deleteVenueById(Integer id) {
        Venue venue = venueRepository.getVenueById(id);
        if (venue == null) {
            throw new CustomNotFoundException("Venue with " + id + " does not exist!");
        }
        return this.venueRepository.deleteVenueById(id);

    }

    @Override
    public Venue InsertNewVenue(RequestVenue venue) {
        return venueRepository.insertNewVenue(venue);
    }
}
