package org.example.eventticketsystem.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.eventticketsystem.model.Request.RequestVenue;
import org.example.eventticketsystem.model.Venue;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues() ;
    Venue getVenueById(Integer id) ;
    Venue UpdateVenueById(RequestVenue venue,Integer id) ;
    Venue deleteVenueById(Integer id);
    Venue InsertNewVenue(RequestVenue venue);
}
