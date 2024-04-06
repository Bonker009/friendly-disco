package org.example.eventticketsystem.service.serviceImpl;

import jakarta.validation.Valid;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.eventticketsystem.exception.CustomNotFoundException;
import org.example.eventticketsystem.model.Event;
import org.example.eventticketsystem.model.Request.RequestEvent;
import org.example.eventticketsystem.model.Venue;
import org.example.eventticketsystem.repository.AttendeeRepository;
import org.example.eventticketsystem.repository.EventRepository;
import org.example.eventticketsystem.repository.VenueRepository;
import org.example.eventticketsystem.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final AttendeeRepository attendeeRepository;

    public EventServiceImpl(EventRepository eventRepository, VenueRepository venueRepository, AttendeeRepository attendeeRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.attendeeRepository = attendeeRepository;
    }


    @Override
    public List<Event> getAllEvent() {
        List<Event> eventList = this.eventRepository.getAllEvents();
        System.out.println(eventList);
        if (eventList == null) {
            throw new CustomNotFoundException("No Event Found!");
        }
        return eventList;
    }

    @Override
    public Event getEventById(Integer id) {
        Event event = this.eventRepository.getEventById(id);
        if (event == null) {
            throw new CustomNotFoundException("Event with ID : " + id + " does not exists!");
        }
        return event;
    }

    @Override
    public Event UpdateEventById(RequestEvent event, Integer id) {
        Event event1 = this.eventRepository.getEventById(id);
        System.out.println(event1 + " update event");
        if (event1 == null) {
            throw new CustomNotFoundException("Event with ID : " + id + " does not exists!");
        }
        for (Integer attendeeId : event.getAttendeesId()) {
            if (this.attendeeRepository.getAttendeeById(attendeeId) == null) {
                throw new CustomNotFoundException("Attendee with ID : " + attendeeId + " does not exists!");
            }
            System.out.println(attendeeId);
            this.eventRepository.updateAttendeeInEventBridgeTable(event1.getEventId(), attendeeId);
        }
        return this.eventRepository.updateEventById(event, id);
    }

    @Override
    public Event deleteEventById(Integer id) {
        Event event = this.eventRepository.getEventById(id);
        if (event == null) {
            throw new CustomNotFoundException("Event with ID : " + id + " does not exists!");
        }
        return this.eventRepository.deleteEventById(id);
    }

    @Override
    public Event InsertNewEvent(@Valid RequestEvent event) {
//        System.out.println(this.eventRepository.insertNewEvent(event) + "the service Impl");
        if (this.venueRepository.getVenueById(event.getVenueId()) == null) {
            throw new CustomNotFoundException("Venue with ID : " + event.getVenueId() + " does not exist!");
        }
        Event event1 = this.eventRepository.insertNewEvent(event);
        System.out.println(event.getAttendeesId() + " attendeeId in the request");
        for (Integer attendeeId : event.getAttendeesId()) {
            System.out.println("------------------");
            this.eventRepository.insertAttendeesToEvent(event1.getEventId(), attendeeId);
            System.out.println(attendeeId + " Attendee Id");
            System.out.println(event1.getEventId() + " eventId");
            System.out.println("------------------");
        }
        System.out.println();
        return event1;
    }

    @Override
    public void insertAttendeesToEvent(Integer eventId, Integer attendeeId) {
        if (attendeeRepository.getAttendeeById(attendeeId) == null) {
            throw new CustomNotFoundException("Attendee with ID : " + attendeeId + " does not exist!");
        }
        this.eventRepository.insertAttendeesToEvent(eventId, attendeeId);
    }

    @Override
    public void updateAttendeeInEventBridge(Integer eventId, Integer attendeeId) {
        this.eventRepository.updateAttendeeInEventBridgeTable(eventId, attendeeId);
    }

}
