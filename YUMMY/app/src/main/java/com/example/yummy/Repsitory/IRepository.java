package com.example.yummy.Repsitory;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.DataBase.WeekPlanDao;
import com.example.yummy.Model.DataBase.favouriteDao;
import com.example.yummy.Model.Network.RemoteDataSource;
import com.example.yummy.Model.Network.NetWorkCallBack;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.MealPlan;

import java.util.List;

public interface IRepository {

    public  void getRemoteRandomMeal(RemoteDataSource remoteSource, NetWorkCallBack netWorkCallBack) ;
    public  void getRemoteCategoreis(RemoteDataSource remoteSource, NetWorkCallBack netWorkCallBack) ;
    public void getRemoteCounrtyMeals(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack) ;
    public void getRemoteCountries(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack) ;
    public  void getRemoteCounrtyMealsFilter(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack ,String country ) ;
    public  void getRemoteCategoryMealsFilter(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack ,String category ) ;
    public  void getRemoteIngredientMealsFilter(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack ,String category ) ;
    public  void getRemoteMealSearcByName(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack ,String MealName ) ;
    public  void getRemoteMealDetailsByName(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack ,String MealName ) ;
    public  void getRemoteIngredient(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack) ;

    public LiveData<List<MealDetails>> getAllFavouriteMeals(favouriteDao favouriteDAO);
    public void deleteFavouriteMeal(favouriteDao favouriteDAO, MealDetails mealDetails) ;
    public void insertMealToFavourite(favouriteDao favouriteDAO, MealDetails mealDetails) ;
    public LiveData<MealDetails> getfavouriteMealByname(favouriteDao favouriteDao , String mealName) ;

    public LiveData<List<MealPlan>> getAllPlanMeals(WeekPlanDao weekPlanDao ) ;
    public LiveData<List<MealPlan>> getPlanMealsByDay(WeekPlanDao weekPlanDao , String dayName) ;
    public void deleteMealFromWeekPlan(WeekPlanDao weekPlanDao , MealPlan mealPlan) ;
    public void insetMealToWeekPlan(WeekPlanDao weekPlanDao  ,MealPlan mealPlan) ;
    public LiveData<MealDetails> getMealFromPlanByName(WeekPlanDao weekPlanDao , String mealName) ;


}
