package com.example.food_map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food_map.entity.Menu;
import com.example.food_map.entity.RestaurantAndMenu;

                                    //���D�䥻����class, �U�����D�䪺class
public interface MenuDao extends JpaRepository<Menu, RestaurantAndMenu>{
	
	public List<Menu> findBystoreName(String storeName);
	
}
