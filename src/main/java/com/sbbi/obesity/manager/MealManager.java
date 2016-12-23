package com.sbbi.obesity.manager;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.sbbi.obesity.dao.MealDaoImpl;
import com.sbbi.obesity.dao.MealFoodDaoImpl;
import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.Meal;
import com.sbbi.obesity.model.SendMeal;
import com.sbbi.obesity.model.TypeMeal;

public class MealManager {

	private Connection connection;
	
	public MealManager(Connection connection) {
		this.connection = connection;
	}

	public void createMeal(Meal meal, double quantity[]){
		
		MealDaoImpl mealDao = new MealDaoImpl(connection);
		int mealId = mealDao.insert(meal);
		//mealDao.close();
		
		for(int i = 0; i < meal.getFoods().size(); i++){
			meal.getFoods().get(i).changeAmountGrams(quantity[i]);
		}
		
		/*MealFood mealFood = new MealFood();
		MealFoodDaoImpl mealFoodDao = new MealFoodDaoImpl(connection);
		mealFoodDao.insert(meal, mealId, quantity);*/
		
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void post(SendMeal sendMeal) {
		
		int mealId = saveMeal(sendMeal);
		
		//saveMealFood
		
	}

	private int saveMeal(SendMeal sendMeal) {
		
		Meal meal = new Meal();
		meal.setDate(new Date()).setTypeMeal(new TypeMeal(1));
		
		MealDaoImpl mealDao = new MealDaoImpl(connection);
		int mealId = mealDao.insert(meal);
		
		return mealId;
	}
	
}
