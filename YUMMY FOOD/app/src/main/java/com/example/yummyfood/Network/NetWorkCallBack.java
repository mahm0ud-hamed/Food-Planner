package com.example.yummyfood.Network;

import com.example.yummyfood.Model.RandomMeal;

import java.util.List;

public interface NetWorkCallBack {

    public void onSuccessResult(List<RandomMeal> products) ;
    public void onFaluireResult(String Messgae) ;

}
