package com.example.yummy.RandoMealPresenter;

public interface IRemoteDataPresnetr {

    public void getRemoteRandomMeal() ;
    public void getRemoteCatigoreis();
    public void getRemoteCountryMeals() ;
    public void getRemoteCountries() ;

    public void getRemoteFilterCounrtyMeal(String counry) ;

    public void getRemoteFilterCategoryMeal(String category) ;

}
