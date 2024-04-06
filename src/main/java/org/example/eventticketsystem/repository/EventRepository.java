package org.example.eventticketsystem.repository;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.example.eventticketsystem.model.Event;
import org.example.eventticketsystem.model.Request.RequestEvent;

@Mapper
public interface EventRepository {
    @Results(id = "eventMapping", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "org.example.eventticketsystem.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id", javaType = List.class, one = @One(select = "org.example.eventticketsystem.repository.AttendeeRepository.selectAttendeeWithEventId"))
    })
    @Select("""
            SELECT * FROM events
            """)
    List<Event> getAllEvents();

    @Select("""
                INSERT INTO events (event_id,event_name, venue_id, event_date) VALUES (DEFAULT,#{requestEvent.eventName} , #{requestEvent.venueId}, #{requestEvent.eventDate}) RETURNING *
            """)
    @ResultMap("eventMapping")
    Event insertNewEvent(@Param("requestEvent") RequestEvent requestEvent);

    @Select("""
            SELECT * FROM events WHERE event_id = #{eventId}
            """)
    @ResultMap("eventMapping")
    Event getEventById(int eventId);

    @Select("""
                UPDATE events SET event_name = #{event.eventName} ,event_date = #{event.eventDate},venue_id = #{event.venueId} WHERE event_id = #{eventId}
            """)
    Event updateEventById(@Param("event") RequestEvent requestEvent, Integer eventId);

    @Select("""
            DELETE FROM events WHERE event_id = #{eventId} RETURNING *
            """)
    Event deleteEventById(int eventId);

    @Insert("""
            INSERT INTO event_attendee (event_id, attendee_id) VALUES (#{eventId},#{attendeeId})
            """)
    void insertAttendeesToEvent(Integer eventId, Integer attendeeId);

    @Update("""
            UPDATE event_attendee SET attendee_id = #{attendeeId} WHERE event_id = #{eventId}
            """)
    void updateAttendeeInEventBridgeTable(@Param("eventId") Integer eventId, @Param("attendeeId") Integer attendeeId);

}
