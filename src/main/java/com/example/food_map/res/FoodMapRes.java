package com.example.food_map.res;

import com.example.food_map.entity.Menu;
import com.example.food_map.entity.Restaurant;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodMapRes {

	private String message;

	private Restaurant restaurant;
	
	private Menu menu;

	public FoodMapRes() {

	}

	public FoodMapRes(String message) {

		this.message = message;
	}

	public FoodMapRes(Restaurant restaurant, String message) {
		this.restaurant = restaurant;
		this.message = message;
	}
	
	public FoodMapRes(Menu menu, String message) {
		this.menu = menu;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
