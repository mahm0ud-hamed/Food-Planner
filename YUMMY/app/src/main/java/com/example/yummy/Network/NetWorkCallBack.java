package com.example.yummy.Network;

import com.example.yummy.Model.RandomMeal;

import java.util.List;

public interface NetWorkCallBack {

    public void onSuccessResult(List<RandomMeal> products) ;
    public void onFaluireResult(String Messgae) ;

}
