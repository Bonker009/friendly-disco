package org.example.eventticketsystem.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private Integer venueId;
    @NotBlank
    @NotNull
    @NotEmpty
    private String venueName;
    @NotBlank
    @NotNull
    @NotEmpty
    private String location;
}
