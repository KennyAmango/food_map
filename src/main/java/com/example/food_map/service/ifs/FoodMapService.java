package com.example.food_map.service.ifs;

import com.example.food_map.req.FoodMapReq;
import com.example.food_map.res.FoodMapResList;

public interface FoodMapService {
	
	public FoodMapResList addRestaurant(FoodMapReq req); 
	
	public FoodMapResList addMenu(FoodMapReq req);
	

}
