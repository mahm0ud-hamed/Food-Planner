package com.example.yummy.MealPresenter;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.MealPlan;

import java.util.List;

public interface ILocalDataPresenter {
    public LiveData<List<MealDetails >> getAllFavouriteMeals() ;
    public void deleteMealFromFavourite(MealDetails mealDetails) ;
    public void insertMealToFavorite(MealDetails mealDetails) ;
    public LiveData<MealDetails> getFavouriteMealByName(String mealName) ;

    public LiveData<List<MealPlan>> getAllWeekPlanMeals() ;
    public LiveData<List<MealPlan>> getMealsFromPlanByDay(String dayName) ;
    public void deleteMealFromWeekPlan(MealPlan mealPlan ) ;
    public void insertMealToWeekPlan(MealPlan mealPlan) ;
    public LiveData<MealDetails> getMealFromPlanByName(String mealName) ;

}
