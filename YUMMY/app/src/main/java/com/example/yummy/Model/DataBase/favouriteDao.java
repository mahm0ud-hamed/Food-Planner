package com.example.yummy.Model.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.yummy.Model.Pojos.MealDetails;

import java.util.List;

@Dao
public interface favouriteDao {
    @Query("SELECT * FROM favourite_table")
    LiveData<List<MealDetails>> getAllFavouriteMeasl() ;

    @Insert
    void insertMealToFavourite(MealDetails mealDetails) ;

    @Delete
    void deleteMealFromFavourite(MealDetails mealDetails) ;

    @Query("SELECT * FROM favourite_table WHERE strMeal = :mealName LIMIT 1")
    LiveData<MealDetails> getMealByName(String mealName) ;

}
