package com.example.yummy.Model;

public class Meal {
    public String strMeal;
    public String strMealThumb;
    public String idMeal;


    public String getIdMeal() {
        return idMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public Meal(String strCountryMeal, String strCountyMealThumb, String idCounrtyMeal) {
        this.strMeal = strCountryMeal;
        this.strMealThumb = strCountyMealThumb;
        this.idMeal = idCounrtyMeal;
    }



}

