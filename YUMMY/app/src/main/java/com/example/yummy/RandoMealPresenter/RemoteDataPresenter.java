package com.example.yummy.RandoMealPresenter;

import com.example.yummy.Model.Category;
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.Model.RandomMeal;
import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.Network.NetWorkCallBack;
import com.example.yummy.Repsitory.Reposiory;
import com.example.yummy.Repsitory.IRepository;
import com.example.yummy.ui.home.IHomeView;

import java.util.List;

public class RemoteDataPresenter implements IRemoteDataPresnetr, NetWorkCallBack {

    // refrence from Iview
    IHomeView homeView;

    // refrence from remote data source
    RemoteDataSource remoteSource ;


    // refrence from repository to call with
         IRepository reposiory ;
    /* will take an radom meal view from fragment */
    public RemoteDataPresenter(RemoteDataSource mealRemoteDataSourceC , IHomeView randoMealView ){

        this.remoteSource = mealRemoteDataSourceC ;
        this.homeView = randoMealView ;
    }
    @Override
    public void getRemoteRandomMeal() {
        reposiory  = new Reposiory();
        reposiory.getRemoteRandomMeal(remoteSource  ,this);
    }

    @Override
    public void getRemoteCatigoreis() {
        reposiory.getRemoteCategoreis(remoteSource ,this);
    }

    @Override
    public void getRemoteCountryMeals() {
        reposiory.getRemoteCounrtyMeals(remoteSource , this);
    }

    @Override
    public void onRMealSuccessResult(List<RandomMeal> meal) {
        homeView.displayRandoMeal(meal);
    }

    @Override
    public void onRMealFaluireResult(String Messgae) {
        homeView.displayRandomMealError(Messgae);
    }

    @Override
    public void onCategoriesSucessResult(List<Category> categories) {
            homeView.displayCategory(categories);
    }

    @Override
    public void onCategoriesFailResult(String Message) {
        homeView.displayCategoryError(Message);
    }



    @Override
    public void onCountyMealSuccessResult(List<CountryMeal> countryMeals) {
        // call home view display counrty image
        homeView.displayCountryMeals(countryMeals);

    }

    @Override
    public void onCountyMealFailResult(String message) {
        // call home view fail message
        homeView.displayCounrtyMealError(message);
    }
}
