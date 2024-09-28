package com.example.yummy.Network;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    private static final String baseUrl= "https://www.themealdb.com/api/json/v1/1/";
    private static RemoteDataSource remoteDataSource = null ;

    private ServiceRequests serviceRequests ;

    private RemoteDataSource(){
        /*on contructor fetching daat from server */
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceRequests = retrofit.create(ServiceRequests.class);

    }

    public static RemoteDataSource getInstance(){
        /*mthod to create on instance from from Product clinet because it was a singltoon*/
        if(remoteDataSource == null ){
            remoteDataSource = new RemoteDataSource();
        }
        return remoteDataSource;
    }

    public void makeMealNetworkCall(NetWorkCallBack netWorkDelegate){
        /*getting response from netwrok */
        Call<RanndomMealResponse> call = serviceRequests.getRandomMeal();
        call.enqueue(new Callback<RanndomMealResponse>() {
            @Override
            public void onResponse(Call<RanndomMealResponse> call, Response<RanndomMealResponse> response) {
                if (response.isSuccessful()) {
                    /*using the refrence of network callback which is implemented by class who need data "PRESENTER"
                     * to handle the logic will applied on the returned data */
                    netWorkDelegate.onRMealSuccessResult(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<RanndomMealResponse> call, Throwable t) {
                /* using the reference of the interface which contain call backs , to print  */
                netWorkDelegate.onRMealFaluireResult(t.getMessage());
            }
        });

    }

    public void makeCategoryNetworkCall (NetWorkCallBack netWorkDelegate){
        /*getting response from netwrok */
        Call<CategoryResponse> call = serviceRequests.getAllCategries();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    /*using the refrence of network callback which is implemented by class who need data "PRESENTER"
                     * to handle the logic will applied on the returned data */
                    netWorkDelegate.onCategoriesSucessResult(response.body().categories);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                /* using the reference of the interface which contain call backs , to print  */
                netWorkDelegate.onCategoriesFailResult(t.getMessage());
            }

        });

    }

    public void makeCounrtyMealsNetworkCall (NetWorkCallBack netWorkDelegate){
        /*getting response from netwrok */
        Call<CounrtyMealResponse> call = serviceRequests.getAllCountryMeals();
        call.enqueue(new Callback<CounrtyMealResponse>() {
            @Override
            public void onResponse(Call<CounrtyMealResponse> call, Response<CounrtyMealResponse> response) {
                if (response.isSuccessful() ){
                    /*using the refrence of network callback which is implemented by class who need data "PRESENTER"
                     * to handle the logic will applied on the returned data */
                    System.out.println("respnse was got");
                    netWorkDelegate.onCountyMealSuccessResult(response.body().meals);
                }else {

                    Log.e("API Response", "Response body is null");
                }
            }

            @Override
            public void onFailure(Call<CounrtyMealResponse> call, Throwable t) {
                System.out.println("response is not getten ");
                /* using the reference of the interface which contain call backs , to print  */
                netWorkDelegate.onCountyMealFailResult(t.getMessage());
            }

        });

    }


    public void makeCounrtriesNetworkCall (NetWorkCallBack netWorkDelegate){
        /*getting response from netwrok */
        Call<CountriesResponse> call = serviceRequests.getAllCountries();
        call.enqueue(new Callback<CountriesResponse>() {
            @Override
            public void onResponse(Call<CountriesResponse> call, Response<CountriesResponse> response) {
                if (response.isSuccessful() ){
                    /*using the refrence of network callback which is implemented by class who need data "PRESENTER"
                     * to handle the logic will applied on the returned data */
                    System.out.println("respnse was got");
                    netWorkDelegate.onCounrtySuccessResult(response.body().meals);
                }else {

                    Log.e("API Response", "Response body is null");
                }
            }

            @Override
            public void onFailure(Call<CountriesResponse> call, Throwable t) {
                System.out.println("response is not getten ");
                /* using the reference of the interface which contain call backs , to print  */
                netWorkDelegate.onCounrtyFailResult(t.getMessage());
            }

        });

    }


}
