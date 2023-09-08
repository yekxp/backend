package com.developers.hireasenior.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

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
    private String status;
    private Date startDate;
    private Date endDate;
    private Double hourlyPrice;
    private String createdAt;
    private String updatedAt;
}
