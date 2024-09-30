package com.example.yummy.ui.dashboard;

import com.example.yummy.Model.CountryMeal;

import java.util.List;

public interface IMealsListView {
    public void viewCountryMealsByFilter(List<CountryMeal> filterCountryMeals) ;
    public void viewCategoryMealsByFilter(List<CountryMeal> filterCategoryMeals) ;
    public void viewIngredientMealsByFilter(List<CountryMeal> filterIngredientMeals) ;

}
