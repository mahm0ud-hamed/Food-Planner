package com.example.yummy.ui.dashboard;

import com.example.yummy.Model.Category;
import com.example.yummy.Model.Counrty;
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.Model.Ingredient;

import java.util.List;

public interface ISearchView {
    public void viewSearchCategory(List<Category> categories) ;
    public void viewSearchCounrty(List<Counrty> counrties) ;
    public void viewIngredient(List<Ingredient> ingredients) ;

    public void viewSearchMealBYName(List<CountryMeal> searchedMeals) ;
}
