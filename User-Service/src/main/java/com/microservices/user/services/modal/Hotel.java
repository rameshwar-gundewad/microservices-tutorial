package com.microservices.user.services.modal;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Hotel {

    private long hotelId;

    private String hotelName;

    private String location;

    private String about;
}
