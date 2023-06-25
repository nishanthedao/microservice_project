package com.hotel.service.services.impl;

import com.hotel.service.repositories.HotelRepo;
import com.hotel.service.services.HotelService;
import com.hotel.service.entity.Hotel;
import com.hotel.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo userRepo;

    @Override
    public Hotel saveUser(Hotel hotel) {
        hotel.setHotelId(UUID.randomUUID().toString());
        Hotel hotel1 = userRepo.save(hotel);
        return hotel1;
    }

    @Override
    public List<Hotel> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Hotel getUserById(String userId) {
        return userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with given id : "+userId));
    }
}
