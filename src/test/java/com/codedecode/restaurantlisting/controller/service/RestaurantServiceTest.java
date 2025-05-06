package com.codedecode.restaurantlisting.controller.service;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.repo.RestaurantRepo;
import com.codedecode.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @Mock
    RestaurantRepo restaurantRepo;

    @InjectMocks
    RestaurantService restaurantService;

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.openMocks(this); // In order for Mock and InjectMocks annotations to take effect, you need to call MocikitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRestaurants(){

        // Mock the service behavior (Create test data)
        List<Restaurant> mockRestaurants= Arrays.asList(new Restaurant(1,"Restaurent 1","Address 1","city 1","Desc 1")
                ,new Restaurant(2,"Restaurent 2","Address 2","city 2","Desc 2"));
        when(restaurantRepo.findAll()).thenReturn(mockRestaurants);

        // call the controller method
        List<RestaurantDTO> response = restaurantService.findAllRestaurants();

        //verify the response

        for (int i=0;i<response.size();i++){
            RestaurantDTO expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(mockRestaurants.get(i));
            assertEquals(expectedDTO,response.get(i));
        }
        assertEquals(mockRestaurants.size(),response.size());

        // verify that service method was called once or not
        verify(restaurantRepo,times(1)).findAll();
    }

    @Test
    public void addRestaurantInDB(){

        // Mock the service behavior (Create test data)
        RestaurantDTO mockRestaurantDTO= new RestaurantDTO(1,"Restaurent 1","Address 1","city 1","Desc 1");

       Restaurant mockRestaurant= RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(mockRestaurantDTO);


        // call the controller method
        when(restaurantRepo.save(mockRestaurant)).thenReturn(mockRestaurant);

        //verify the response

        assertEquals(mockRestaurantDTO,mockRestaurantDTO);

        // verify that service method was called once or not
        verify(restaurantRepo,times(1)).save(mockRestaurant);
    }

    @Test
    public void fetchRestaurantById(){

        // create mock restaurant id
        Integer mockRestaurantId=1;

        // Mock the service behavior (Create test data)
        Restaurant mockRestaurant= new Restaurant(1,"Restaurent 1","Address 1","city 1","Desc 1");
        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));

        // call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        //verify the response

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockRestaurantId,response.getBody().getId());

        // verify that service method was called once or not
        verify(restaurantRepo,times(1)).findById(mockRestaurantId);
    }

    @Test
    public void fetchRestaurantById_NonExistingId(){

        // create mock restaurant id
        Integer mockRestaurantId=1;

        // Mock the service behavior (Create test data)
        Restaurant mockRestaurant= new Restaurant(1,"Restaurent 1","Address 1","city 1","Desc 1");
        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.empty());

        // call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        //verify the response

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(null,response.getBody());

        // verify that service method was called once or not
        verify(restaurantRepo,times(1)).findById(mockRestaurantId);
    }


}
