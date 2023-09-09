package com.developers.hireasenior.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "sessions")
public class Session {
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
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

}
