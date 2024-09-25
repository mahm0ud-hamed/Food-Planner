package com.example.yummyfood.Network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface mealServiceRequest  {
    @GET("random.php")
    Call<RanndomMealResponse> getRandomMeal() ;

}
