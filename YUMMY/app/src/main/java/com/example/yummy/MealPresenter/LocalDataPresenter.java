package com.example.yummy.MealPresenter;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.DataBase.MealDao;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Repsitory.Reposiory;
import com.example.yummy.ui.Favourite.IFavouriteView;

import java.util.List;

public class LocalDataPresenter implements ILocalDataPresenter{
    private MealDao mealDao;

    public LocalDataPresenter(MealDao mealDAO){
        this.mealDao = mealDAO ;

    }
    @Override
    public LiveData<List<MealDetails>> getAllFavouriteMeals() {

        return new Reposiory().getAllFavouriteMeals(mealDao);
    }

    @Override
    public void deleteMealFromFavourite(MealDetails mealDetails) {
        new Reposiory().deleteFavouriteMeal(mealDao , mealDetails);
    }

    @Override
    public void insertMealToFavorite(MealDetails mealDetails) {
        new Reposiory().insertMealToFavourite(mealDao , mealDetails);
    }
}
