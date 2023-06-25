package com.hotel.service.services;

import com.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveUser(Hotel hotel);

    List<Hotel> getAllUsers();

    Hotel getUserById(String userId);

}
