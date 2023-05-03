package com.example.food_map.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.food_map.entity.Menu;
import com.example.food_map.entity.Restaurant;
import com.example.food_map.entity.RestaurantAndMenu;
import com.example.food_map.repository.MenuDao;
import com.example.food_map.repository.RestaurantDao;
import com.example.food_map.req.FoodMapReq;
import com.example.food_map.res.FoodMapRes;
import com.example.food_map.res.FoodMapResList;
import com.example.food_map.service.ifs.FoodMapService;

@Service
public class FoodMapServiceImpl implements FoodMapService {

	@Autowired
	private RestaurantDao restaurantDao;

	@Autowired
	private MenuDao menuDao;

	@Transactional
	@Override
	public FoodMapResList addRestaurant(FoodMapReq req) {

		if (req.getRestaurantList().isEmpty()) {
			return new FoodMapResList("請輸入要新增的店名和城市");
		}

		List<FoodMapRes> foodMapResList = new ArrayList<>();
		List<Restaurant> restaurantList = new ArrayList<>();
		List<String> messageList = new ArrayList<>();

		int x = 0;
		for (Restaurant item : req.getRestaurantList()) {
			x++;
			boolean checked = true;

			if (!StringUtils.hasText(item.getStoreName())) {
				messageList.add("第" + x + "筆資料:" + "請輸入要新增的店名");
				checked = false;
			}
			if (!StringUtils.hasText(item.getCity())) {
				messageList.add("第" + x + "筆資料:" + "請輸入要新增的城市");
				checked = false;
			}

			if (restaurantDao.existsById(item.getStoreName())) {
				messageList.add("第" + x + "筆資料:" + "店名已存在");
				checked = false;
			}
			if (restaurantList.size() > 0) {

				for (Restaurant item2 : restaurantList) {

					if (item.getStoreName().equals(item2.getStoreName())) {

						messageList.add("第" + x + "筆資料:" + "店名與" + "前面重複，無法創建");
						checked = false;
						break;
					}
				}
			}

			if (checked == false) {
				continue;
			}

			Restaurant restaurant = new Restaurant(item.getStoreName(), item.getCity());
			FoodMapRes foodMapRes = new FoodMapRes(restaurant, "創建成功");
			restaurantList.add(restaurant);
			foodMapResList.add(foodMapRes);
		}

		restaurantDao.saveAll(restaurantList);
		FoodMapResList res = new FoodMapResList();
		res.setFoodMapResList(foodMapResList);
		res.setMessageList(messageList);

		return res;
	}

	@Override
	public FoodMapResList addMenu(FoodMapReq req) {

		if (req.getMenuList().isEmpty()) {
			return new FoodMapResList("請輸入要新增的店名和餐點名稱");
		}

		List<FoodMapRes> foodMapResList = new ArrayList<>();
		List<Menu> menuList = new ArrayList<>();
		List<String> messageList = new ArrayList<>();

		// 開始檢查要新增的餐點資料是否符合
		int x = 0;
		for (Menu item : req.getMenuList()) {

			x++;

			Optional<Restaurant> op = restaurantDao.findById(item.getStoreName());
			if (!op.isPresent()) {
				messageList.add("第" + x + "筆資料:" + "無此店家，無法新增");
				continue;
			}

			boolean checked = true;

			if (!StringUtils.hasText(item.getStoreName())) {
				messageList.add("第" + x + "筆資料:" + "請輸入餐點的店名");
				checked = false;
			}
			if (!StringUtils.hasText(item.getStoreMenu())) {
				messageList.add("第" + x + "筆資料:" + "請輸入要新增的餐點名稱");
				checked = false;
			}
			if (item.getPrice() == null || item.getPrice() < 0) {
				messageList.add("第" + x + "筆資料:" + "餐點價格評價不能為空，或小於0");
				checked = false;
			}
			if (item.getMenuStar() == null || item.getMenuStar() <= 0) {
				messageList.add("第" + x + "筆資料:" + "餐點評價不能為空，或小於1");
				checked = false;
			}
			if (menuList.size() > 0) {

				for (Menu item2 : menuList) {

					if (item.getStoreName().equals(item2.getStoreName())
							&& item.getStoreMenu().equals(item2.getStoreMenu())) {

						messageList.add("第" + x + "筆資料:" + "店名跟餐點名稱" + "與前面重複，無法新增");
						checked = false;
						break;
					}
				}
			}
			RestaurantAndMenu menuId = new RestaurantAndMenu(item.getStoreName(), item.getStoreMenu());

			if (menuDao.existsById(menuId)) {
				messageList.add("第" + x + "筆資料:" + "店名與餐點名稱已存在，無法新增");
				checked = false;
			}

			if (checked == false) {
				continue;
			}

			Menu menu = new Menu(item.getStoreName(), item.getStoreMenu(), item.getPrice(), item.getMenuStar());
			FoodMapRes foodMapRes = new FoodMapRes(menu, "創建成功");
			menuList.add(menu);
			foodMapResList.add(foodMapRes);

			//依據新增的餐點更改店家總評價
			List<Menu> menuOfStore = menuDao.findBystoreName(item.getStoreName());

			int num = menuOfStore.size();
			Restaurant restaurant = op.get();
			float point = restaurant.getStoreStar() * num;
			
			float finalPoint = (point + item.getMenuStar()) / (num + 1);
			
			restaurant.setStoreStar(finalPoint);
			restaurantDao.save(restaurant);
			
		}
		
		menuDao.saveAll(menuList);
		FoodMapResList res = new FoodMapResList();
		if (!menuList.isEmpty()) {
			res.setFoodMapResList(foodMapResList);
		}

		res.setMessageList(messageList);

		return res;
	}

}
