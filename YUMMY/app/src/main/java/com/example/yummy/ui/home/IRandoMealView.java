package com.example.yummy.ui.home;

import com.example.yummy.Model.RandomMeal;

import java.util.List;

public interface IRandoMealView {
    public void displayRandoMeal(List<RandomMeal> Meal) ;
    public  void displayRandomMealError(String Error) ;
}
