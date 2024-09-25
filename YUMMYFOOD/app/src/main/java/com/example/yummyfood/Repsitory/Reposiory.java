package com.example.yummyfood.Repsitory;

import com.example.yummyfood.Model.RandomMeal;
import com.example.yummyfood.Network.NetWorkCallBack;
import com.example.yummyfood.Network.ProductRemoteDataSourceC;

import java.util.List;

public class Reposiory implements RepositoryIN  {

    @Override
    public void getRemoteRandomMeal(ProductRemoteDataSourceC remoteSource, NetWorkCallBack netWorkCallBack) {
        remoteSource.makeNetworkCall(netWorkCallBack);
    }
}
