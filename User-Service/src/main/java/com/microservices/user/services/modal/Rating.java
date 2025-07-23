package com.microservices.user.services.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class Rating {

    private long ratingId;

    private long hotelId;

    private long userId;

    private long rating;

    private Hotel hotel;
}
