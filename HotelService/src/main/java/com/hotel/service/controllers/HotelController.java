package com.hotel.service.controllers;

import com.hotel.service.entity.Hotel;
import com.hotel.service.services.HotelService;
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
    public ResponseEntity<Hotel> createUser(@RequestBody Hotel hotel){
        return new ResponseEntity<Hotel>(hotelService.saveUser(hotel), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllUser(){
        return new ResponseEntity<List<Hotel>>(hotelService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Hotel> getUserById(@PathVariable String userId){
        return new ResponseEntity<Hotel>(hotelService.getUserById(userId), HttpStatus.OK);
    }
}
