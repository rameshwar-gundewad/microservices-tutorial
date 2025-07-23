package com.microservices.rating.service.service;

import com.microservices.rating.service.entity.Rating;
import com.microservices.rating.service.exception.ResourceNotFoundException;
import com.microservices.rating.service.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating findByRatingId(long ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Rating not found by given ratingId !!"));
    }

    @Override
    public List<Rating> findAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> findAllRatingByUserId(long userId) {

        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> findAllRatingByHotelId(long hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
