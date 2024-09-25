package com.example.yummy.Network;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceC{

    private static final String baseUrl= "https://www.themealdb.com/api/json/v1/1/";
    private static MealRemoteDataSourceC remoteDataSource = null ;

    private mealServiceRequest mealRequest ;

    private MealRemoteDataSourceC(){
        /*on contructor fetching daat from server */
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealRequest = retrofit.create(mealServiceRequest.class);

    }

    public static MealRemoteDataSourceC getInstance(){
        /*mthod to create on instance from from Product clinet because it was a singltoon*/
        if(remoteDataSource == null ){
            remoteDataSource = new MealRemoteDataSourceC();
        }
        return remoteDataSource;
    }

    public void makeNetworkCall (NetWorkCallBack netWorkDelegate){
        /*getting response from netwrok */
        Call<RanndomMealResponse> call = mealRequest.getRandomMeal();
        call.enqueue(new Callback<RanndomMealResponse>() {
            @Override
            public void onResponse(Call<RanndomMealResponse> call, Response<RanndomMealResponse> response) {
                if (response.isSuccessful()) {
                    /*using the refrence of network callback which is implemented by class who need data "PRESENTER"
                     * to handle the logic will applied on the returned data */
                    netWorkDelegate.onSuccessResult(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<RanndomMealResponse> call, Throwable t) {
                /* using the reference of the interface which contain call backs , to print  */
                netWorkDelegate.onFaluireResult(t.getMessage());
            }
        });

    }


}
