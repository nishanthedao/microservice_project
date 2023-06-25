package com.hotel.service.entity;


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
@Table(name = "micro_hotel")
public class Hotel {
    @Id
    private String hotelId;
    private String name;
    // ohter user propery that you want
}
