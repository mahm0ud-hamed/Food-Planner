package com.example.yummy.Repsitory;

import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.Network.NetWorkCallBack;

public class Reposiory implements IRepository {

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
