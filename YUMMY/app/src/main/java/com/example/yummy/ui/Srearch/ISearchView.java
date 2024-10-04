package com.example.yummy.ui.Srearch;

import com.example.yummy.Model.Pojos.Category;
import com.example.yummy.Model.Pojos.Counrty;
import com.example.yummy.Model.Pojos.Meal;
import com.example.yummy.Model.Pojos.Ingredient;

import java.util.List;

public interface ISearchView {
    public void viewSearchCategory(List<Category> categories) ;
    public void viewSearchCounrty(List<Counrty> counrties) ;
    public void viewIngredient(List<Ingredient> ingredients) ;

    public void viewSearchMealBYName(List<Meal> searchedMeals) ;
}
