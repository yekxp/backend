package com.developers.hireasenior.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "session_requests")
public class SessionRequest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToOne
    @JoinColumn(name = "juniorId", referencedColumnName = "id")
    private Account junior;
    @ManyToOne
    @JoinColumn(name = "seniorId", referencedColumnName = "id")
    private Account senior;
    private SessionStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double hourlyPrice;
    private String createdAt;
    private String updatedAt;
}
