package com.microservices.rating.service.controller;

import com.microservices.rating.service.entity.Rating;
import com.microservices.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.saveRating(rating));
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> findByRatingId(@PathVariable long ratingId) {
        return ResponseEntity.ok(ratingService.findByRatingId(ratingId));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> findAllRating() {
        return ResponseEntity.ok(ratingService.findAllRating());
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Rating>> findAllRatingByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(ratingService.findAllRatingByUserId(userId));
    }

    @GetMapping("/hotelId/{hotelId}")
    public ResponseEntity<List<Rating>> findAllRatingByHotelId(@PathVariable long hotelId) {
        return ResponseEntity.ok(ratingService.findAllRatingByHotelId(hotelId));
    }
}
