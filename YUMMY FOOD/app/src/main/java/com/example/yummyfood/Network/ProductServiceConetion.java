package com.example.yummyfood.Network;

import com.example.mvpdesignpattern.Model.ProductJson;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductServiceConetion {
    @GET("random.php")
    Call<RanndomMealResponse> getRandomMeal() ;

}
