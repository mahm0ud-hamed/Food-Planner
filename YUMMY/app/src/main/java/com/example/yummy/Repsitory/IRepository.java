package com.example.yummy.Repsitory;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.DataBase.MealDao;
import com.example.yummy.Model.Network.RemoteDataSource;
import com.example.yummy.Model.Network.NetWorkCallBack;
import com.example.yummy.Model.Pojos.MealDetails;

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

    public LiveData<List<MealDetails>> getAllFavouriteMeals(MealDao mealDAO);
    public void deleteFavouriteMeal(MealDao mealDAO , MealDetails mealDetails) ;
    public void insertMealToFavourite(MealDao mealDAO , MealDetails mealDetails) ;


}
