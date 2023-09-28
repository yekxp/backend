package com.developers.hireasenior.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String text;
    private String juniorId;
    private String seniorId;

    private String date;
    private String rating;
}
