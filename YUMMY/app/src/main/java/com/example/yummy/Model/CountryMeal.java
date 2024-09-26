package com.example.yummy.Model;

public class CountryMeal {
    public String strCountryMeal;
    public String strCountyMealThumb;

    public String getIdCounrtyMeal() {
        return idCounrtyMeal;
    }

    public String getStrCountyMealThumb() {
        return strCountyMealThumb;
    }

    public String getStrCountryMeal() {
        return strCountryMeal;
    }

    public CountryMeal(String strCountryMeal, String strCountyMealThumb, String idCounrtyMeal) {
        this.strCountryMeal = strCountryMeal;
        this.strCountyMealThumb = strCountyMealThumb;
        this.idCounrtyMeal = idCounrtyMeal;
    }

    public String idCounrtyMeal ;

}

