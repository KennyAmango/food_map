package com.example.food_map.entity;

import java.io.Serializable;

@SuppressWarnings("serial")  //����RestaurantAndMenu��@Serializable�ɥX�{�����׽u

//�U�����D�䪺class
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
