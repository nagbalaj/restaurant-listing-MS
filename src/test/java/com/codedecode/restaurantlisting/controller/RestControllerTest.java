package com.codedecode.restaurantlisting.controller;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestControllerTest {

    @InjectMocks
    RestController restController;

    @Mock
    RestaurantService restaurantService;

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.openMocks(this); // In order for Mock and InjectMocks annotations to take effect, you need to call MocikitoAnnotations.openMocks(this);
    }
    @Test
    public void testFetchAllRestaurants(){

        // Mock the service behavior (Create test data)
        List<RestaurantDTO> mockAllRestaurants= Arrays.asList(new RestaurantDTO(1,"Restaurent 1","Address 1","city 1","Desc 1")
                                                          ,new RestaurantDTO(2,"Restaurent 2","Address 2","city 2","Desc 2"));
           when(restaurantService.findAllRestaurants()).thenReturn(mockAllRestaurants);

           // call the controller method
        ResponseEntity<List<RestaurantDTO>> reponse = restController.fetchAllRestaurants();

        //verify the response

        assertEquals(HttpStatus.OK,reponse.getStatusCode());
        assertEquals(mockAllRestaurants,reponse.getBody());

        // verify that service method was called
        verify(restaurantService,times(1)).findAllRestaurants();


    }

    @Test
    public void testSaveRestaurant(){

        // Mock the service behavior (Create test data)
        RestaurantDTO mockRestaurant= new RestaurantDTO(1,"Restaurent 1","Address 1","city 1","Desc 1");
        when(restaurantService.addRestaurantInDB(mockRestaurant)).thenReturn(mockRestaurant);

        // call the controller method
        ResponseEntity<RestaurantDTO> respose = restController.saveRestaurant(mockRestaurant);

        //verify the response

        assertEquals(HttpStatus.CREATED,respose.getStatusCode());
        assertEquals(mockRestaurant,respose.getBody());

        // verify that service method was called
        verify(restaurantService,times(1)).addRestaurantInDB(mockRestaurant);


    }

    @Test
    public void testAddRestaurantById(){

        // Mock the service behavior (Create test data)
        RestaurantDTO mockRestaurant= new RestaurantDTO(1,"Restaurent 1","Address 1","city 1","Desc 1");

        // create mock restaurant id
        Integer mockRestaurantId=1;

        when(restaurantService.fetchRestaurantById(mockRestaurantId)).thenReturn( new ResponseEntity<>(mockRestaurant,HttpStatus.OK));

        // call the controller method
        ResponseEntity<RestaurantDTO> respose = restController.addRestaurantById(mockRestaurantId);

        //verify the response

        assertEquals(HttpStatus.OK,respose.getStatusCode());

        assertEquals(mockRestaurant,respose.getBody());

        // verify that service method was called
        verify(restaurantService,times(1)).fetchRestaurantById(mockRestaurantId);


    }
}
