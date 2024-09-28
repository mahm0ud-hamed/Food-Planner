package com.example.yummy.Network;

import com.example.yummy.Model.Category;
import com.example.yummy.Model.Counrty;
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.Model.RandomMeal;

import java.util.List;

public interface NetWorkCallBack {

    public void onRMealSuccessResult(List<RandomMeal> products) ;
    public void onRMealFaluireResult(String message) ;

    public void onCategoriesSucessResult(List<Category> categories ) ;
    public void onCategoriesFailResult(String message) ;

    public void onCountyMealSuccessResult(List<CountryMeal> countryMeals) ;
    public void onCountyMealFailResult(String message) ;

    public void onCounrtySuccessResult(List<Counrty> counrties) ;
    public void onCounrtyFailResult(String message) ;

    public  void onCountryMealFilterSucssessResult(List<CountryMeal> countryMeals) ;
    public  void onCountryMealFilterFailResult(String message ) ;


    public  void oncategoryMealFilterFailResult(String message ) ;
    public  void oncategoryMealFilterSucssessResult(List<CountryMeal> countryMeals) ;

}
