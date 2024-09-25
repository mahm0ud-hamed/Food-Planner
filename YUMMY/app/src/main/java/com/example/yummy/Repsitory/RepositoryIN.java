package com.example.yummy.Repsitory;

import com.example.yummy.Network.MealRemoteDataSourceC;
import com.example.yummy.Network.NetWorkCallBack;
import com.example.yummy.Network.MealRemoteDataSourceC;

public interface RepositoryIN {

    public  void getRemoteRandomMeal(MealRemoteDataSourceC remoteSource, NetWorkCallBack netWorkCallBack) ;

}
