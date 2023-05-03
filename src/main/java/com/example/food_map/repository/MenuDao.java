package com.example.food_map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food_map.entity.Menu;
import com.example.food_map.entity.RestaurantAndMenu;

                                    //雙主鍵本身的class, 託管雙主鍵的class
public interface MenuDao extends JpaRepository<Menu, RestaurantAndMenu>{
	
	public List<Menu> findBystoreName(String storeName);
	
}
