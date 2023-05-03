package com.example.food_map.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@Column(name = "store_name")
	private String storeName;

	@Column(name = "city")
	private String city;

	@Column(name = "store_star")
	private float storeStar;

	public Restaurant() {

	}

	public Restaurant(String storeName, String city) {
		this.storeName = storeName;
		this.city = city;
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

	public float getStoreStar() {
		return storeStar;
	}

	public void setStoreStar(float storeStar) {
		this.storeStar = storeStar;
	}

	

}
