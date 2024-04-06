package org.example.eventticketsystem.service.serviceImpl;


import org.example.eventticketsystem.exception.CustomNotFoundException;
import org.example.eventticketsystem.model.Attendee;
import org.example.eventticketsystem.model.Request.RequestAttendee;

import org.example.eventticketsystem.repository.AttendeeRepository;
import org.example.eventticketsystem.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAllAttendees() {
        return this.attendeeRepository.getAllAttendees();
    }

    @Override
    public Attendee getAttendeeById(Integer id) {
        Attendee attendee = this.attendeeRepository.getAttendeeById(id);
        if (attendee == null) {
            throw new CustomNotFoundException("Attendee with " + id + " does not exist!");
        }
        return this.attendeeRepository.getAttendeeById(id);
    }

    @Override
    public Attendee UpdateAttendeeById(RequestAttendee attendee, Integer id) {
        Attendee venue = this.attendeeRepository.getAttendeeById(id);
        if (venue == null) {
            throw new CustomNotFoundException("Attendee with " + id + " does not exist!");
        }
        return this.attendeeRepository.updateAttendeeById(attendee, id);
    }
    @Override
    public Attendee deleteAttendeeById(Integer id) {
        Attendee venue = this.attendeeRepository.getAttendeeById(id);
        if (venue == null) {
            throw new CustomNotFoundException("Attendee with " + id + " does not exist!");
        }
        return this.attendeeRepository.deleteAttendeeById(id);
    }

    @Override
    public Attendee InsertNewAttendee(RequestAttendee attendee) {
        return this.attendeeRepository.insertNewAttendee(attendee);
    }
}
