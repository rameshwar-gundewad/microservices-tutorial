package com.microservices.user.services.service;

import com.microservices.user.services.entity.User;
import com.microservices.user.services.exception.ResourceNotFoundException;
import com.microservices.user.services.external.HotelService;
import com.microservices.user.services.external.RatingService;
import com.microservices.user.services.modal.Hotel;
import com.microservices.user.services.modal.Rating;
import com.microservices.user.services.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    // commented code because here using not FeignClient to call external Rest API
   /* @Value("${rating.url}")
    private String ratingUrl;

    @Value("${hotel.url}")
    private String hotelUrl;*/

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {
        logger.info("UserServiceImpl:: Got payload :: {}",user);
        return userRepository.save(user);
    }

    @Override
    public User findUserById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with give ID not found in server !! :"+userId));
         /*
             commented code because here using not FeignClient to call external Rest API
             Rating[] ratingOfUser = restTemplate.getForObject(ratingUrl+userId,Rating[].class);
          */
        List<Rating> ratings = ratingService.getRatingByUserId(userId);
        logger.info("Consume ratings message from rest  template :: {}",ratings);
        ratings.stream().map(rating -> {
            /*
                commented code because here using not FeignClient to call external Rest API
                Hotel hotel  = restTemplate.getForObject(hotelUrl+rating.getHotelId(),Hotel.class);
            */
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).toList();
        user.setRatings(ratings);
        return user;
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
