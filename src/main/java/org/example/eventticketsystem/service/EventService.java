package org.example.eventticketsystem.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.eventticketsystem.model.Event;
import org.example.eventticketsystem.model.Request.RequestEvent;
import org.example.eventticketsystem.model.Request.RequestVenue;
import org.example.eventticketsystem.model.Venue;

import java.util.List;

public interface EventService {
    List<Event> getAllEvent();
    Event getEventById(Integer id);
    Event UpdateEventById(RequestEvent event, Integer id);
    Event deleteEventById(Integer id);
    Event InsertNewEvent(RequestEvent event);
    void insertAttendeesToEvent(Integer eventId,Integer attendeeId);
    void updateAttendeeInEventBridge(Integer eventId,Integer attendeeId);



}
