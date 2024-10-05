package com.example.yummy.MealPresenter;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.DataBase.WeekPlanDao;
import com.example.yummy.Model.DataBase.favouriteDao;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.MealPlan;
import com.example.yummy.Repsitory.Reposiory;

import java.security.PrivateKey;
import java.util.List;

public class LocalDataPresenter implements ILocalDataPresenter{
    private favouriteDao favouriteDao;
    private WeekPlanDao weekPlanDao ;

    public LocalDataPresenter(favouriteDao favouriteDAO){
        this.favouriteDao = favouriteDAO;

    }
    public LocalDataPresenter(WeekPlanDao weekPlanDao){
        this.weekPlanDao = weekPlanDao ;
    }
    @Override
    public LiveData<List<MealDetails>> getAllFavouriteMeals() {

        return new Reposiory().getAllFavouriteMeals(favouriteDao);
    }

    @Override
    public void deleteMealFromFavourite(MealDetails mealDetails) {
        new Reposiory().deleteFavouriteMeal(favouriteDao, mealDetails);
    }

    @Override
    public void insertMealToFavorite(MealDetails mealDetails) {
        new Reposiory().insertMealToFavourite(favouriteDao, mealDetails);
    }

    @Override
    public LiveData<MealDetails> getFavouriteMealByName(String mealName) {
        return  new Reposiory().getfavouriteMealByname(favouriteDao,mealName) ;
    }

    @Override
    public LiveData<List<MealPlan>> getAllWeekPlanMeals() {
        return new Reposiory().getAllPlanMeals(weekPlanDao);
    }

    @Override
    public LiveData<List<MealPlan>> getMealsFromPlanByDay(String dayName) {

        return new Reposiory().getPlanMealsByDay(weekPlanDao , dayName);
    }

    @Override
    public void deleteMealFromWeekPlan(MealPlan mealPlan) {
        new Reposiory().deleteMealFromWeekPlan(weekPlanDao , mealPlan);
    }

    @Override
    public void insertMealToWeekPlan(MealPlan mealPlan) {
       new Reposiory().insetMealToWeekPlan(weekPlanDao , mealPlan);
    }
}
