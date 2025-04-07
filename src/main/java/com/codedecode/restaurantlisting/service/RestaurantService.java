package com.codedecode.restaurantlisting.service;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepo restaurantRepo;

    public List<RestaurantDTO> findAllRestaurants() {

        List<Restaurant> restaurants = restaurantRepo.findAll();

        // map to the list of DTO's

        List<RestaurantDTO> restaurantsDTO = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());


        return restaurantsDTO ;
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {

        // map restaurantDTO to restaurant to store in DB
         Restaurant savedRestaurant=  RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO);

         // save in db
         restaurantRepo.save(savedRestaurant);

         // map restaurant to restaurantDTO to return
        RestaurantDTO savedRestaurantDTO=  RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
         return savedRestaurantDTO;
    }

    public ResponseEntity<RestaurantDTO>  fetchRestaurantById(Integer id) {
        
        // fetch restaurant from db
        Optional<Restaurant> restaurant= restaurantRepo.findById(id);

       

        if (restaurant.isPresent()) {
            // map restaurant to restaurantDTO to return
            RestaurantDTO restaurantDTO=  RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get());
            return ResponseEntity.ok(restaurantDTO);
            
        }else{

            return new  ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        
    }
}
