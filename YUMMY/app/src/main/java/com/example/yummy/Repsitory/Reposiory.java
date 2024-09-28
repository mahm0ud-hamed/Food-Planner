package com.example.yummy.Repsitory;

import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.Network.NetWorkCallBack;

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
}
