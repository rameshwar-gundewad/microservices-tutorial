package com.microservices.rating.service.repository;

import com.microservices.rating.service.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    public List<Rating> findByUserId(long userId);

    public List<Rating> findByHotelId(long hotelId);
}
