package com.example.yummy.Model.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceRequests {
    /*fetchinig random meal*/
    @GET("random.php")
    Call<RanndomMealResponse> getRandomMeal();

    /*fetching categories */
    @GET("categories.php")
    Call<CategoryResponse> getAllCategries() ;

    /*fetchin meals of specific country */
    @GET("filter.php?a=Canadian")
    Call<CounrtyMealResponse> getAllCountryMeals() ;

    /*fetching all countries names */
    @GET("list.php?a=list")
    Call<CountriesResponse> getAllCountries() ;

    /*fetching meals filted by country */
     @GET("filter.php")
    Call<CounrtyMealResponse> filterMealsByCounrty(@Query("a") String country);

     /*fetching meals filtered by category*/
     @GET("filter.php")
     Call<CounrtyMealResponse> filterMealsByCategory(@Query("c") String country);

    @GET("list.php?i=list")
    Call<IngredientResponse> getAllIngredient() ;

    @GET("filter.php")
    Call<CounrtyMealResponse> filterMealByIngredient(@Query("i") String ingredient) ;

    @GET("search.php")
    Call<CounrtyMealResponse> searchMealByName(@Query("s") String mealName) ;

    @GET("search.php")
    Call<MealDetailResponse> searchMealDetailByName(@Query("s") String mealName) ;

}
