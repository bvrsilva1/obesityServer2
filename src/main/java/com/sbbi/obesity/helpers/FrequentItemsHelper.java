package com.sbbi.obesity.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.sbbi.obesity.model.Food;
import com.sbbi.obesity.model.FrequentItems;
import com.sbbi.obesity.model.FrequentItemsComparator;
import com.sbbi.obesity.model.Meal;

public class FrequentItemsHelper {

	//public static List<FrequentItems> listFrequentItems(List<Meal> myMealList) {
	public static List<FrequentItems> listFrequentItems(List<Meal> myMealList) {
		
		HashMap<String, List<Food>> frequentItems = new HashMap<String, List<Food>>();
		
		for(Meal meal : myMealList){
			for(Food food : meal.getFoods()){
				
				if("Carrot".equals(food.getName())){
					System.out.println("carrot");
				}
				
				if(hasName(frequentItems, food.getName())){
					List<Food> listFood = frequentItems.get(food.getName());
					listFood.add(food);
					frequentItems.put(food.getName(), listFood);
				}
				else{
					List<Food> listFood = new ArrayList<Food>();
					listFood.add(food);
					frequentItems.put(food.getName(), listFood);
				}
				
			}
		}
		
		List<FrequentItems> listFrequency = getCaloriesFromEachFood(frequentItems);
		
		Collections.sort(listFrequency, new FrequentItemsComparator());//by calories and frequency
		//Collections.sort(listFrequency);//only by one field
		print(listFrequency);
		
		return listFrequency;
		
	}

	private static void print(List<FrequentItems> listFrequency) {
		for(FrequentItems f : listFrequency){
			System.out.println("Food: " + f.getName() + "\tFrequency: " + f.getFrequency() + "\tCalories: " + f.getCalories());
		}
		
	}

	private static List<FrequentItems> getCaloriesFromEachFood(HashMap<String, List<Food>> frequentItems) {
		
		List<FrequentItems> listFrequent = new ArrayList<FrequentItems>();
		
		for(String key : frequentItems.keySet()){
			List<Food> listFood = frequentItems.get(key);
			double calories = getCalories(listFood);
			FrequentItems f = new FrequentItems(key, listFood.size(), calories);
			listFrequent.add(f);
			
			//System.out.println("Food: " + key + "\tFrequency: " + listFood.size() + "\tCalories: " + calories);
		}
		
		return listFrequent;
	}

	private static double getCalories(List<Food> listFood) {
		double calories = 0;
		
		for(Food food : listFood){
			calories = calories + food.getEnergy();
		}
		
		return calories;
	}

	private static boolean hasName(HashMap<String, List<Food>> frequentItems, String name) {
		if(frequentItems.containsKey(name))
			return true;
		
		return false;
	}

	public static double calculateCaloriesIn(List<FrequentItems> listFrequentItems) {
		
		double totalCaloriesIn = 0;
		
		for(FrequentItems items : listFrequentItems){
			totalCaloriesIn = totalCaloriesIn + items.getCalories();
		}
		
		return totalCaloriesIn;
	}

}