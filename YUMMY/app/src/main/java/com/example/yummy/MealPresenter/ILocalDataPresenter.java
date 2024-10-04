package com.example.yummy.MealPresenter;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.Pojos.MealDetails;

import java.util.List;

public interface ILocalDataPresenter {
    public LiveData<List<MealDetails >> getAllFavouriteMeals() ;
    public void deleteMealFromFavourite(MealDetails mealDetails) ;
    public void insertMealToFavorite(MealDetails mealDetails) ;
}
