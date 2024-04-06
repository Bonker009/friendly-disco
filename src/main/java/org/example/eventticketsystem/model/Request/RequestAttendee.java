package org.example.eventticketsystem.model.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAttendee {
    @NotBlank
    @NotNull
    private String attendeeName;
    @NotBlank
    @NotNull
    @Email
    private String email;
}
