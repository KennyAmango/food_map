package com.example.food_map.req;

import java.util.List;

import com.example.food_map.entity.Menu;
import com.example.food_map.entity.Restaurant;

public class FoodMapReq {
	
	private String storeName;
	
	private String city;
	
	private String storeMenu;

	private Integer price;
	
	private List<Restaurant> restaurantList;
	
	private List<Menu> menuList;
	
	public FoodMapReq(){
		
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Restaurant> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<Restaurant> restaurantList) {
		this.restaurantList = restaurantList;
	}

	public String getStoreMenu() {
		return storeMenu;
	}

	public void setStoreMenu(String storeMenu) {
		this.storeMenu = storeMenu;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	
	
	

}
