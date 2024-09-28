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


    public void getRemoteCategoruMealsFilter(RemoteDataSource remoteDataSource, NetWorkCallBack netWorkCallBack, String category){
        remoteDataSource.makeCategoriesMealFilterNetworkCall(netWorkCallBack , category);
    }

}
