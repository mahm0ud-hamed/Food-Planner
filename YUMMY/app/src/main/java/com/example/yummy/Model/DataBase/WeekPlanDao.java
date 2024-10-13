package com.example.yummy.Model.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yummy.Model.Pojos.MealPlan;

import java.util.List;
@Dao
public interface WeekPlanDao {

    @Query("SELECT * FROM weekPlan_table")
    LiveData<List<MealPlan>> getAllWeekPlanMeals() ;

     @Query("SELECT * FROM weekPlan_table WHERE planDayDate = :dayDate")
    LiveData<List<MealPlan>> getMealFromPlanByDay(String dayDate) ;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMealToWeekPlan(MealPlan mealPlan) ;

    @Delete
    void deleteMealFromWeekPlan(MealPlan mealPlan) ;

}
