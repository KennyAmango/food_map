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
			return new FoodMapResList("�п�J�n�s�W�����W�M����");
		}

		List<FoodMapRes> foodMapResList = new ArrayList<>();
		List<Restaurant> restaurantList = new ArrayList<>();
		List<String> messageList = new ArrayList<>();

		int x = 0;
		for (Restaurant item : req.getRestaurantList()) {
			x++;
			boolean checked = true;

			if (!StringUtils.hasText(item.getStoreName())) {
				messageList.add("��" + x + "�����:" + "�п�J�n�s�W�����W");
				checked = false;
			}
			if (!StringUtils.hasText(item.getCity())) {
				messageList.add("��" + x + "�����:" + "�п�J�n�s�W������");
				checked = false;
			}

			if (restaurantDao.existsById(item.getStoreName())) {
				messageList.add("��" + x + "�����:" + "���W�w�s�b");
				checked = false;
			}
			if (restaurantList.size() > 0) {

				for (Restaurant item2 : restaurantList) {

					if (item.getStoreName().equals(item2.getStoreName())) {

						messageList.add("��" + x + "�����:" + "���W�P" + "�e�����ơA�L�k�Ы�");
						checked = false;
						break;
					}
				}
			}

			if (checked == false) {
				continue;
			}

			Restaurant restaurant = new Restaurant(item.getStoreName(), item.getCity());
			FoodMapRes foodMapRes = new FoodMapRes(restaurant, "�Ыئ��\");
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
			return new FoodMapResList("�п�J�n�s�W�����W�M�\�I�W��");
		}

		List<FoodMapRes> foodMapResList = new ArrayList<>();
		List<Menu> menuList = new ArrayList<>();
		List<String> messageList = new ArrayList<>();

		// �}�l�ˬd�n�s�W���\�I��ƬO�_�ŦX
		int x = 0;
		for (Menu item : req.getMenuList()) {

			x++;

			Optional<Restaurant> op = restaurantDao.findById(item.getStoreName());
			if (!op.isPresent()) {
				messageList.add("��" + x + "�����:" + "�L�����a�A�L�k�s�W");
				continue;
			}

			boolean checked = true;

			if (!StringUtils.hasText(item.getStoreName())) {
				messageList.add("��" + x + "�����:" + "�п�J�\�I�����W");
				checked = false;
			}
			if (!StringUtils.hasText(item.getStoreMenu())) {
				messageList.add("��" + x + "�����:" + "�п�J�n�s�W���\�I�W��");
				checked = false;
			}
			if (item.getPrice() == null || item.getPrice() < 0) {
				messageList.add("��" + x + "�����:" + "�\�I����������ର�šA�Τp��0");
				checked = false;
			}
			if (item.getMenuStar() == null || item.getMenuStar() <= 0) {
				messageList.add("��" + x + "�����:" + "�\�I�������ର�šA�Τp��1");
				checked = false;
			}
			if (menuList.size() > 0) {

				for (Menu item2 : menuList) {

					if (item.getStoreName().equals(item2.getStoreName())
							&& item.getStoreMenu().equals(item2.getStoreMenu())) {

						messageList.add("��" + x + "�����:" + "���W���\�I�W��" + "�P�e�����ơA�L�k�s�W");
						checked = false;
						break;
					}
				}
			}
			RestaurantAndMenu menuId = new RestaurantAndMenu(item.getStoreName(), item.getStoreMenu());

			if (menuDao.existsById(menuId)) {
				messageList.add("��" + x + "�����:" + "���W�P�\�I�W�٤w�s�b�A�L�k�s�W");
				checked = false;
			}

			if (checked == false) {
				continue;
			}

			Menu menu = new Menu(item.getStoreName(), item.getStoreMenu(), item.getPrice(), item.getMenuStar());
			FoodMapRes foodMapRes = new FoodMapRes(menu, "�Ыئ��\");
			menuList.add(menu);
			foodMapResList.add(foodMapRes);

			//�̾ڷs�W���\�I��况�a�`����
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
