package com.example.yummy.ui.home;

import com.example.yummy.Model.Category;
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.Model.RandomMeal;

import java.util.List;

public interface IHomeView {
    /*Random meal */
    public void displayRandoMeal(List<RandomMeal> Meal) ;
    public void displayRandomMealError(String Error) ;

    /*meal by category */
    public void displayCategory(List<Category> categories) ;
    public void displayCategoryError(String Error ) ;

    /*meal by county */
    public void displayCountryMeals(List<CountryMeal> countryMeals) ;
    public void displayCounrtyMealError(String Error ) ;
}
