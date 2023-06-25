package com.user.service.services.impl;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.external.services.HotelService;
import com.user.service.external.services.RatingService;
import com.user.service.repositories.UserRepo;
import com.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        User user1 = userRepo.save(user);
        return user1;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepo.findAll();
        List<User> collect = userList.stream().map(u -> {
//            u.setRating(restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + u.getUserId(), ArrayList.class));
            u.setRating(Arrays.asList(ratingService.getRating(u.getUserId())));
            u.getRating().stream().map(r -> {
//                r.setHotel(restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + r.getHotelId(), Hotel.class));
                r.setHotel(hotelService.getHotel(r.getHotelId()));
                return r;
            }).collect(Collectors.toList());
            return u;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with given id : "+userId));
        // Fetch rating of the above user from Rating service
//        Rating[] forObject = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        Rating[] forObject = ratingService.getRating(user.getUserId());
        logger.info("{}",forObject);
        List<Rating> updatedRating = Arrays.asList(forObject).stream().map(r -> {
//            r.setHotel(restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + r.getHotelId(), Hotel.class));
            r.setHotel(hotelService.getHotel(r.getHotelId()));
            return r;
        }).collect(Collectors.toList());
        user.setRating(updatedRating);
        return user;
    }
}
