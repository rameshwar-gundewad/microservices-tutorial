package com.microservices.hotel.service.service;

import com.microservices.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {

    public Hotel saveHotel(Hotel hotel);

    public List<Hotel> findAllHotel();

    public Hotel findByHotelId(long hotelId);
}
