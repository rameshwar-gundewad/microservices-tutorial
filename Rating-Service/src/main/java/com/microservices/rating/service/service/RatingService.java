package com.microservices.rating.service.service;

import com.microservices.rating.service.entity.Rating;

import java.util.List;

public interface RatingService {

    public Rating saveRating(Rating rating);

    public Rating findByRatingId(long ratingId);

    public List<Rating> findAllRating();

    public List<Rating> findAllRatingByUserId(long userId);

    public List<Rating> findAllRatingByHotelId(long hotelId);
}
