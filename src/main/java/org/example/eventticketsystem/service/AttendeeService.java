package org.example.eventticketsystem.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.eventticketsystem.model.Attendee;
import org.example.eventticketsystem.model.Request.RequestAttendee;
import org.example.eventticketsystem.model.Request.RequestEvent;
import org.example.eventticketsystem.model.Venue;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees() ;
    Attendee getAttendeeById(Integer id);
    Attendee UpdateAttendeeById(RequestAttendee attendee, Integer id) ;
    Attendee deleteAttendeeById(Integer id) ;
    Attendee InsertNewAttendee(RequestAttendee attendee);

}
