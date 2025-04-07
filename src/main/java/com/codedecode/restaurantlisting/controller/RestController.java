package com.codedecode.restaurantlisting.controller;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/restaurant")
@CrossOrigin    // enables any request coming from different origin and handle request its own .if not used then request is  not going from frontend to backend
public class RestController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants(){

       List<RestaurantDTO> allRestaurants= restaurantService.findAllRestaurants();

       return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO){
      RestaurantDTO restaurantDTOAdded=  restaurantService.addRestaurantInDB(restaurantDTO);

        return new ResponseEntity<>(restaurantDTOAdded,HttpStatus.CREATED);
    }


    @GetMapping("/fetchRestaurantById/{id}")
    public ResponseEntity<RestaurantDTO> addRestaurantById(@PathVariable Integer id){
         

        return restaurantService.fetchRestaurantById(id);
    }





}
