package com.microservices.user.services.external;

import com.microservices.user.services.modal.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/userId/{userId}")
    public List<Rating> getRatingByUserId(@PathVariable long userId);
}
