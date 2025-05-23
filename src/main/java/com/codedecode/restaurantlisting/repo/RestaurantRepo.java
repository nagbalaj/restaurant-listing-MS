package com.codedecode.restaurantlisting.repo;

import com.codedecode.restaurantlisting.entity.Restaurant;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {
}
