package com.example.yummy.Repsitory;

import android.widget.ThemedSpinnerAdapter;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.DataBase.WeekPlanDao;
import com.example.yummy.Model.DataBase.favouriteDao;
import com.example.yummy.Model.Network.RemoteDataSource;
import com.example.yummy.Model.Network.NetWorkCallBack;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.MealPlan;

import java.util.List;

public class Reposiory implements IRepository {


        // constructor nee to take remota and locsa data source
    @Override
    public void getRemoteRandomMeal(RemoteDataSource remoteSource, NetWorkCallBack netWorkCallBack) {
        remoteSource.makeMealNetworkCall(netWorkCallBack);
    }

    @Override
    public void getRemoteCategoreis(RemoteDataSource remoteSource, NetWorkCallBack netWorkCallBack) {
        remoteSource.makeCategoryNetworkCall(netWorkCallBack);
    }

    @Override
    public void getRemoteCounrtyMeals(RemoteDataSource remoteDataSource, NetWorkCallBack netWorkCallBack) {
        remoteDataSource.makeCounrtyMealsNetworkCall(netWorkCallBack);
    }

    @Override
    public void getRemoteCountries(RemoteDataSource remoteDataSource, NetWorkCallBack netWorkCallBack) {
        remoteDataSource.makeCounrtriesNetworkCall(netWorkCallBack);
    }

    @Override
    public void getRemoteCounrtyMealsFilter(RemoteDataSource remoteDataSource, NetWorkCallBack netWorkCallBack, String country) {
        remoteDataSource.makeCounrtriesMealFilterNetworkCall(netWorkCallBack , country);
    }

    @Override
    public void getRemoteCategoryMealsFilter(RemoteDataSource remoteDataSource, NetWorkCallBack netWorkCallBack, String category) {
        remoteDataSource.makeCategoriesMealFilterNetworkCall(netWorkCallBack , category);
    }

    @Override
    public void getRemoteIngredientMealsFilter(RemoteDataSource remoteDataSource, NetWorkCallBack netWorkCallBack, String ingredient ) {
        remoteDataSource.makeIngredientMealFilterNetworkCall( netWorkCallBack , ingredient);
    }

    @Override
    public void getRemoteMealSearcByName(RemoteDataSource remoteDataSource, NetWorkCallBack netWorkCallBack, String MealName) {
        remoteDataSource.makeSearchMealByNameNetworkCall(netWorkCallBack , MealName );
    }

    @Override
    public void getRemoteMealDetailsByName(RemoteDataSource remoteDataSource, NetWorkCallBack netWorkCallBack, String mealName) {
        remoteDataSource.makeSearchMealDetailByNameNetworkCall(netWorkCallBack,mealName);
    }

    @Override
    public void getRemoteIngredient(RemoteDataSource remoteDataSource, NetWorkCallBack netWorkCallBack) {
        remoteDataSource.makeIngredientNetworkCall(netWorkCallBack);
    }

    @Override
    public LiveData<List<MealDetails>> getAllFavouriteMeals(favouriteDao favouriteDAO) {
        return favouriteDAO.getAllFavouriteMeasl() ;
    }

    @Override
    public void deleteFavouriteMeal(favouriteDao favouriteDAO, MealDetails mealDetails) {

       new Thread(()->{
            favouriteDAO.deleteMealFromFavourite(mealDetails);
        }).start();
    }

    @Override
    public void insertMealToFavourite(favouriteDao favouriteDAO, MealDetails mealDetails) {
        new Thread(()->{
            favouriteDAO.insertMealToFavourite(mealDetails);
        }).start();
    }

    @Override
    public LiveData<MealDetails> getfavouriteMealByname(favouriteDao favouriteDao, String mealName) {
       return favouriteDao.getMealByName(mealName);
    }

    @Override
    public LiveData<List<MealPlan>> getAllPlanMeals(WeekPlanDao weekPlanDao) {
        return weekPlanDao.getAllWeekPlanMeals();
    }

    @Override
    public LiveData<List<MealPlan>> getPlanMealsByDay(WeekPlanDao weekPlanDao, String dayName) {
        return weekPlanDao.getMealFromPlanByDay(dayName);
    }

    @Override
    public void deleteMealFromWeekPlan(WeekPlanDao weekPlanDao, MealPlan mealPlan) {
       new Thread(()->{
           weekPlanDao.deleteMealFromWeekPlan(mealPlan);
       }).start();
    }

    @Override
    public void insetMealToWeekPlan(WeekPlanDao weekPlanDao, MealPlan mealPlan) {
       new Thread(()->{
           weekPlanDao.insertMealToWeekPlan(mealPlan);
       }).start();
    }

    @Override
    public LiveData<MealDetails> getMealFromPlanByName(WeekPlanDao weekPlanDao, String mealName) {
        return weekPlanDao.getMealByNamefromPlan(mealName) ;
    }

}
