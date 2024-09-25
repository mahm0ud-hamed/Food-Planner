package com.example.yummyfood.Repsitory;

import com.example.yummyfood.Network.NetWorkCallBack;
import com.example.yummyfood.Network.ProductRemoteDataSourceC;

public interface RepositoryIN {

    public  void getRemoteRandomMeal(ProductRemoteDataSourceC remoteSource, NetWorkCallBack netWorkCallBack) ;

}
