package com.example.yummyfood.RandoMealPresenter;

import com.example.yummyfood.Model.RandomMeal;
import com.example.yummyfood.Network.NetWorkCallBack;
import com.example.yummyfood.Network.ProductRemoteDataSourceC;
import com.example.yummyfood.Repsitory.Reposiory;
import com.example.yummyfood.Repsitory.RepositoryIN;

import java.util.List;

public class RandomMealPresenter  implements IRandomMealPresnetr , NetWorkCallBack {

    // refrence from Iview
    // refrence from remote data source
    ProductRemoteDataSourceC remoteSource ;
    // refrence from Nretwork call back
    NetWorkCallBack netWorkCallBack ;
    // refrence from repository to call with

    /* will take an radom meal view from fragment */
    public RandomMealPresenter(NetWorkCallBack netWorkCallBack ){

        this.netWorkCallBack = netWorkCallBack ;
    }
    @Override
    public void getRemoteRandomMeal() {
       RepositoryIN reposiory  = new Reposiory();
        reposiory.getRemoteRandomMeal(remoteSource , netWorkCallBack);
    }

    @Override
    public void onSuccessResult(List<RandomMeal> products) {

    }

    @Override
    public void onFaluireResult(String Messgae) {

    }
}
