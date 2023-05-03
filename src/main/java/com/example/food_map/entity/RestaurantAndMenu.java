package com.example.food_map.entity;

import java.io.Serializable;

@SuppressWarnings("serial")  //忽略RestaurantAndMenu實作Serializable時出現的黃斜線

//託管雙主鍵的class
public class RestaurantAndMenu implements Serializable{
	
	private String storeName;
	
	private String storeMenu;
	
	public RestaurantAndMenu(){
		
	}
	
	public RestaurantAndMenu(String storeName) {
		this.storeName = storeName;
	}
	
	public RestaurantAndMenu(String storeName, String storeMenu) {
		this.storeName = storeName;
		this.storeMenu = storeMenu;
	}


	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreMenu() {
		return storeMenu;
	}

	public void setStoreMenu(String storeMenu) {
		this.storeMenu = storeMenu;
	}
	
	

}
