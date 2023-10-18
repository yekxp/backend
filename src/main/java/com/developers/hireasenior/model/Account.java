package com.developers.hireasenior.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@Table(name = "accounts")
@JsonPropertyOrder({"id", "firstName", "email", "password", "verified", "role", "title", "hourlyPrice", "currency", "createdAt", "updatedAt"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "sessionRequests", "technologies", "languagesSpoken"})
public class Account implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String firstName;
    private String email;
    private String password;
    private Boolean verified = false;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private Title title;
    private Double hourlyPrice;
    private String currency;

    @OneToMany(mappedBy = "junior")
    private List<SessionRequest> sessionRequests;

    @ManyToMany
    @JoinTable(
            name = "account_technologies",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "languages_spoken",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languagesSpoken = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "developer_available_periods",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "available_period_id")
    )
    private Set<AvailablePeriod> availablePeriods;
    private Date dateOfBirth;

    @CreatedDate
    private Date createdAt = new Date();
    @LastModifiedDate
    private Date updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
