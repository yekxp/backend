package com.developers.hireasenior.dto;

import com.developers.hireasenior.model.Language;
import com.developers.hireasenior.model.Role;
import com.developers.hireasenior.model.Technology;
import com.developers.hireasenior.model.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.language.bm.Lang;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String firstName;
    private String email;
    private String password;
    private Boolean verified;
    private Role role;
    private Title title;
    private Double hourlyPrice;
    private String currency;
    private Set<Technology> technologies;
    private Set<Language> languagesSpoken;
    private String availablePeriod;
    private Date dateOfBirth;
}
