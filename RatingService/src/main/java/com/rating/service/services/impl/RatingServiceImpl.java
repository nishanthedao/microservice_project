package com.rating.service.services.impl;

import com.rating.service.entity.Rating;
import com.rating.service.exception.ResourceNotFoundException;
import com.rating.service.repositories.RatingRepo;
import com.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepo ratingRepo;

    @Override
    public Rating saveRating(Rating rating) {
        rating.setRatingId(UUID.randomUUID().toString());
        Rating rating1 = ratingRepo.save(rating);
        return rating1;
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public Rating getRatingById(String ratingId) {
        return ratingRepo.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with given id : "+ratingId));
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
