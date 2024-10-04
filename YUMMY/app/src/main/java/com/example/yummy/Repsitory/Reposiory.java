package com.example.yummy.Repsitory;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.DataBase.MealDao;
import com.example.yummy.Model.Network.RemoteDataSource;
import com.example.yummy.Model.Network.NetWorkCallBack;
import com.example.yummy.Model.Pojos.MealDetails;

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
    public LiveData<List<MealDetails>> getAllFavouriteMeals(MealDao mealDAO) {
        return mealDAO.getAllFavouriteMeasl() ;
    }

    @Override
    public void deleteFavouriteMeal(MealDao mealDAO, MealDetails mealDetails) {

       new Thread(()->{
            mealDAO.deleteMealFromFavourite(mealDetails);
        }).start();
    }

    @Override
    public void insertMealToFavourite(MealDao mealDAO, MealDetails mealDetails) {
        new Thread(()->{
            mealDAO.insertMealToFavourite(mealDetails);
        }).start();
    }

}
