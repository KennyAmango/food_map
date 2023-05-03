package com.example.food_map.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
// 這是branch1
@Entity
@Table(name = "menu")
@IdClass(value = RestaurantAndMenu.class)
public class Menu {

	@Id
	@Column(name = "store_name")
	//餐廳名稱
	private String storeName;

	@Id
	@Column(name = "store_menu")
	//餐廳餐點
	private String storeMenu;

	@Column(name = "price")
	private Integer price;

	@Column(name = "menu_star")
	private Integer menuStar;

	public Menu() {

	}
	

	public Menu(String storeName, String storeMenu, Integer price, Integer menuStar) {
		this.storeName = storeName;
		this.storeMenu = storeMenu;
		this.price = price;
		this.menuStar = menuStar;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getMenuStar() {
		return menuStar;
	}

	public void setMenuStar(Integer menuStar) {
		this.menuStar = menuStar;
	}

	

}
