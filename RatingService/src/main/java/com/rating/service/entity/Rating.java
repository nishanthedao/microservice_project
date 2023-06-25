package com.rating.service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micro_rating")
public class Rating {
    @Id
    private String ratingId;
    private String hotelId;
    private String userId;
    private String rating;
    private String feedback;
    // ohter user propery that you want
}
