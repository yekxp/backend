package com.developers.hireasenior.dto.request;

import com.developers.hireasenior.model.AvailablePeriod;
import com.developers.hireasenior.model.Language;
import com.developers.hireasenior.model.Technology;
import com.developers.hireasenior.model.Title;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    private Title title;
    private Double hourlyPrice;
    private String currency;
    private Set<Technology> technologies;
    private Set<Language> languagesSpoken;
    private Set<AvailablePeriod> availablePeriods;
    private Date dateOfBirth;
}
