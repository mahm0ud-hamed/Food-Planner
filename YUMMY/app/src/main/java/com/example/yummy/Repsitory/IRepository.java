package com.example.yummy.Repsitory;

import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.Network.NetWorkCallBack;

public interface IRepository {

    public  void getRemoteRandomMeal(RemoteDataSource remoteSource, NetWorkCallBack netWorkCallBack) ;
    public  void getRemoteCategoreis(RemoteDataSource remoteSource, NetWorkCallBack netWorkCallBack) ;
    public void getRemoteCounrtyMeals(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack) ;
    public void getRemoteCountries(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack) ;
    public  void getRemoteCounrtyMealsFilter(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack ,String country ) ;
    public  void getRemoteCategoryMealsFilter(RemoteDataSource remoteDataSource , NetWorkCallBack netWorkCallBack ,String category ) ;



}
