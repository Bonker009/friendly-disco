package org.example.eventticketsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private Integer eventId;
    private String eventName;
    private Date eventDate;
    private Venue venue;
    private List<Attendee> attendees;
}
