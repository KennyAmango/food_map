package com.example.food_map.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodMapResList {
	
	private String message;

	private List<String> messageList;

	private List<FoodMapRes> foodMapResList;

	public FoodMapResList() {

	}

	public FoodMapResList(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<String> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}

	public List<FoodMapRes> getFoodMapResList() {
		return foodMapResList;
	}

	public void setFoodMapResList(List<FoodMapRes> foodMapResList) {
		this.foodMapResList = foodMapResList;
	}

}
