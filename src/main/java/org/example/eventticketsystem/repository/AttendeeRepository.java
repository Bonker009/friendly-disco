package org.example.eventticketsystem.repository;

import org.apache.ibatis.annotations.*;
import org.example.eventticketsystem.model.Attendee;
import org.example.eventticketsystem.model.Request.RequestAttendee;
import org.example.eventticketsystem.model.Venue;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Results(id = "attendeeMapping", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    @Select("""
                SELECT * FROM attendees
            """)
    List<Attendee> getAllAttendees();

    @ResultMap("attendeeMapping")
    @Select("""
            SELECT  * FROM attendees WHERE attendee_id = #{id}
            """)
    Attendee getAttendeeById(Integer id);

    @Select("""
            UPDATE attendees SET email = #{attendee.email} ,attendee_name = #{attendee.attendeeName} WHERE attendee_id = #{id} RETURNING *
            """)
    @ResultMap("attendeeMapping")
    Attendee updateAttendeeById(@Param("attendee") RequestAttendee attendee, Integer id);

    @Select("""
            DELETE FROM attendees WHERE attendee_id = #{id} RETURNING *
            """)
    @ResultMap("attendeeMapping")
    Attendee deleteAttendeeById(Integer id);

    @Select("""
            INSERT INTO attendees VALUES (DEFAULT,#{attendee.attendeeName},#{attendee.email})  RETURNING *
            """)
    @ResultMap("attendeeMapping")
    Attendee insertNewAttendee(@Param("attendee") RequestAttendee attendee);

    @Select("""
            SELECT e.* FROM attendees e JOIN public.event_attendee ea ON e.attendee_id = ea.attendee_id WHERE ea.event_id = #{eventId}                                                                                  """)
    @ResultMap("attendeeMapping")
    List<Attendee> selectAttendeeWithEventId(Integer eventId);

}
