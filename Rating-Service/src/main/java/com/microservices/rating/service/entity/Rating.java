package com.microservices.rating.service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private long ratingId;

    @Column(name = "hotel_id")
    private long hotelId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "rating")
    private long rating;
}
