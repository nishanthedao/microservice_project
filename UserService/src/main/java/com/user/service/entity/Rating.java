package com.user.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Rating {
    private String ratingId;
    private String hotelId;
    private String userId;
    private String rating;
    private String feedback;
    private Hotel hotel = new Hotel();
    // ohter user propery that you want
}
