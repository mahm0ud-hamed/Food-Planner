package com.example.yummy.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceRequests {
    @GET("random.php")
    Call<RanndomMealResponse> getRandomMeal();

    @GET("categories.php")
    Call<CategoryResponse> getAllCategries() ;

    @GET("filter.php?a=Canadian")
    Call<CounrtyMealResponse> getAllCountryMeals() ;

    @GET("list.php?a=list")
    Call<CountriesResponse> getAllCountries() ;

     @GET("filter.php")
    Call<CounrtyMealResponse> filterMealsByCounrty(@Query("a") String country);


     @GET("filter.php")
     Call<CounrtyMealResponse> filterMealsByCategory(@Query("c") String country);

}
