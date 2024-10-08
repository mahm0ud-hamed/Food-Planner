package com.example.yummy.Model.Network;

import com.example.yummy.Model.Pojos.Category;
import com.example.yummy.Model.Pojos.Counrty;
import com.example.yummy.Model.Pojos.Meal;
import com.example.yummy.Model.Pojos.Ingredient;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.RandomMeal;

import java.util.List;

public interface NetWorkCallBack {

    public void onRMealSuccessResult(List<RandomMeal> products) ;
    public void onRMealFaluireResult(String message) ;

    public void onCategoriesSucessResult(List<Category> categories ) ;
    public void onCategoriesFailResult(String message) ;

    public void onCountyMealSuccessResult(List<Meal> meals) ;
    public void onCountyMealFailResult(String message) ;

    public void onCounrtySuccessResult(List<Counrty> counrties) ;
    public void onCounrtyFailResult(String message) ;

    public  void onCountryMealFilterSucssessResult(List<Meal> meals) ;
    public  void onCountryMealFilterFailResult(String message ) ;


    public  void oncategoryMealFilterFailResult(String message ) ;
    public  void oncategoryMealFilterSucssessResult(List<Meal> meals) ;

    public  void onIngerdeintFilterFailResult(String message ) ;
    public  void onIngerdientFilterSucssessResult(List<Meal> meals) ;


    public void onIngredientSuccessResult(List<Ingredient> ingredients) ;
    public void onIngredientFailResult (String message);

    public void onSearchMealByNameSuccessResult(List<Meal> meals) ;
    public void onSearchMealByNameFailResult(String message) ;

    public void onSearchMealDetailByNameSuccessResult(List<MealDetails> mealDetails) ;
    public void onSearchMealDetailByNameFailResult(String meassage) ;



}
