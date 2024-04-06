package org.example.eventticketsystem.model.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEvent {
    @NotBlank
    @NotNull
    @NotEmpty
    private String eventName;
    @NotBlank
    @NotNull
    @NotEmpty
    private Date eventDate;
    @NotBlank
    @NotNull
    @NotEmpty
    private Integer venueId;
    @NotBlank
    @NotNull
    @NotEmpty
    private List<Integer> attendeesId;
}
