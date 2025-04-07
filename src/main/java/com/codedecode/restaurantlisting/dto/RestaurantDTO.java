package com.codedecode.restaurantlisting.dto;

import jdk.jfr.Name;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private  int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
