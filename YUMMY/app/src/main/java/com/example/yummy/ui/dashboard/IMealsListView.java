package com.example.yummy.ui.dashboard;

import com.example.yummy.Model.Meal;

import java.util.List;

public interface IMealsListView {
    public void viewCountryMealsByFilter(List<Meal> filterMeals) ;
    public void viewCategoryMealsByFilter(List<Meal> filterCategoryMeals) ;
    public void viewIngredientMealsByFilter(List<Meal> filterIngredientMeals) ;

}
