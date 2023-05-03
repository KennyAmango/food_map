package com.example.food_map.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food_map.entity.Restaurant;

public interface RestaurantDao extends JpaRepository<Restaurant, String>{
	
	

}
