package com.rating.service.controllers;

import com.rating.service.entity.Rating;
import com.rating.service.services.RatingService;
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
    public ResponseEntity<Rating> createUser(@RequestBody Rating rating){
        return new ResponseEntity<Rating>(ratingService.saveRating(rating), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return new ResponseEntity<List<Rating>>(ratingService.getAllRatings(), HttpStatus.OK);
    }
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable String ratingId){
        return new ResponseEntity<Rating>(ratingService.getRatingById(ratingId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return new ResponseEntity<List<Rating>>(ratingService.getRatingByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return new ResponseEntity<List<Rating>>(ratingService.getRatingByHotelId(hotelId), HttpStatus.OK);
    }
}
