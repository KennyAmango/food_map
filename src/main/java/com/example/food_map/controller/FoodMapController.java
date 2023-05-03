package com.example.food_map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.food_map.req.FoodMapReq;
import com.example.food_map.res.FoodMapResList;
import com.example.food_map.service.ifs.FoodMapService;

@RestController
public class FoodMapController {
	
	@Autowired
	private FoodMapService foodMapService;
	
	@PostMapping("/addRestaurant")
	public FoodMapResList addRestaurant(@RequestBody FoodMapReq req) {
		
		return foodMapService.addRestaurant(req);
		
	}
	
	@PostMapping("/addMenu")
	public FoodMapResList addMenu(@RequestBody FoodMapReq req) {
		
		return foodMapService.addMenu(req);
		
	}
}
