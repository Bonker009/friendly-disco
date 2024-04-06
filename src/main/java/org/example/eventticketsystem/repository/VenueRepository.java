package org.example.eventticketsystem.repository;

import org.apache.ibatis.annotations.*;
import org.example.eventticketsystem.model.Request.RequestVenue;
import org.example.eventticketsystem.model.Venue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface VenueRepository {


    @Select("""
                SELECT * FROM venues
            """)
    @Results(id = "venueMapping", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    List<Venue> getAllVenues();
    @Select("""
                    SELECT * FROM venues WHERE venue_id = #{id}
            """)
    @ResultMap(value = "venueMapping")
    Venue getVenueById(Integer id);

    @Select("""
            UPDATE venues SET venue_name = #{venue.venueName},location = #{venue.lcoation} WHERE venue_id = #{id} RETURNING *
            """)
    @ResultMap(value = "venueMapping")
    Venue updateVenueById(@Param("venue") RequestVenue venue,Integer id);

    @Select("""
            DELETE FROM venues WHERE venue_id = #{id} RETURNING *
            """)
    @ResultMap(value = "venueMapping")
    Venue deleteVenueById(Integer id);

    @Select("""
                    INSERT INTO venues VALUES (DEFAULT,#{venue.venueName},#{venue.location}) RETURNING *
            """)
    @ResultMap(value = "venueMapping")
    Venue insertNewVenue(@Param("venue")RequestVenue venue);
}
