package com.microservices.hotel.service.controller;

import com.microservices.hotel.service.entity.Hotel;
import com.microservices.hotel.service.service.HotelService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotel));

    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> findByHotelId(@PathVariable long hotelId) {
        return ResponseEntity.ok(hotelService.findByHotelId(hotelId));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> findAllHotel(){
        return ResponseEntity.ok(hotelService.findAllHotel());
    }
}
