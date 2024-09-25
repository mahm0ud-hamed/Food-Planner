package com.example.yummy.RandoMealPresenter;

import com.example.yummy.Model.RandomMeal;
import com.example.yummy.Network.MealRemoteDataSourceC;
import com.example.yummy.Network.NetWorkCallBack;
import com.example.yummy.Network.MealRemoteDataSourceC;
import com.example.yummy.Repsitory.Reposiory;
import com.example.yummy.Repsitory.RepositoryIN;
import com.example.yummy.ui.home.IRandoMealView;

import java.util.List;

public class RandomMealPresenter  implements IRandomMealPresnetr , NetWorkCallBack {

    // refrence from Iview
    // refrence from remote data source
    MealRemoteDataSourceC remoteSource ;
    // refrence from Nretwork call back
    NetWorkCallBack netWorkCallBack ;
    IRandoMealView randoMealView ;
    // refrence from repository to call with

    /* will take an radom meal view from fragment */
    public RandomMealPresenter(MealRemoteDataSourceC mealRemoteDataSourceC , IRandoMealView randoMealView ){

        this.remoteSource = mealRemoteDataSourceC ;
        this.randoMealView = randoMealView ;
    }
    @Override
    public void getRemoteRandomMeal() {
       RepositoryIN reposiory  = new Reposiory();
        reposiory.getRemoteRandomMeal(remoteSource  ,this);
    }

    @Override
    public void onSuccessResult(List<RandomMeal> meal) {
        randoMealView.displayRandoMeal(meal);
    }

    @Override
    public void onFaluireResult(String Messgae) {
        randoMealView.displayRandomMealError(Messgae);
    }
}
