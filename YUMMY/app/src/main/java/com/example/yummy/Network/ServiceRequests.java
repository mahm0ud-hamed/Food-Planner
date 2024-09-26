package com.example.yummy.Network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceRequests {
    @GET("random.php")
    Call<RanndomMealResponse> getRandomMeal();

    @GET("categories.php")
    Call<CategoryResponse> getAllCategries() ;

    @GET("filter.php?a=egyptian")
    Call<CounrtyMealREsponse> getAllCountyMeals() ;


}
