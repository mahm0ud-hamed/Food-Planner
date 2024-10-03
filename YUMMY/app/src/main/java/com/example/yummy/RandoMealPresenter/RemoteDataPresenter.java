package com.example.yummy.RandoMealPresenter;

import com.example.yummy.Model.Category;
import com.example.yummy.Model.Counrty;
import com.example.yummy.Model.Meal;
import com.example.yummy.Model.Ingredient;
import com.example.yummy.Model.MealDetails;
import com.example.yummy.Model.RandomMeal;
import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.Network.NetWorkCallBack;
import com.example.yummy.Repsitory.Reposiory;
import com.example.yummy.Repsitory.IRepository;
import com.example.yummy.ui.Details.IMealDetail;
import com.example.yummy.ui.dashboard.IMealsListView;
import com.example.yummy.ui.dashboard.ISearchView;
import com.example.yummy.ui.home.IHomeView;

import java.util.List;

public class RemoteDataPresenter implements IRemoteDataPresnetr, NetWorkCallBack {

    // refrence from Iview
    IHomeView homeView;

    // refrence from Search home
    ISearchView searchView ;
    // refrence from remote data source
    RemoteDataSource remoteSource ;
    IMealsListView mealsListView ;
    IMealDetail mealDetail ;


    // refrence from repository to call with
         IRepository reposiory  = new Reposiory();
    /* will take an radom meal view from fragment */
    public RemoteDataPresenter(RemoteDataSource mealRemoteDataSourceC , IHomeView randoMealView ){

        this.remoteSource = mealRemoteDataSourceC ;
        this.homeView = randoMealView ;
    }

    public RemoteDataPresenter(RemoteDataSource mealRemoteDataSourceC , ISearchView iSearchView){
        this.remoteSource = mealRemoteDataSourceC ;
        this.searchView = iSearchView ;

    }

    public RemoteDataPresenter(RemoteDataSource mealRemoteDataSourceC , IMealsListView mealsListView){
        this.remoteSource = mealRemoteDataSourceC ;
        this.mealsListView = mealsListView ;

    }
    public RemoteDataPresenter(RemoteDataSource mealRemoteDataSourceC , IMealDetail mealDetail){
        this.remoteSource = mealRemoteDataSourceC ;
        this.mealDetail = mealDetail ;

    }
    @Override
    public void getRemoteRandomMeal() {

        reposiory.getRemoteRandomMeal(remoteSource  ,this);
    }

    @Override
    public void getRemoteCatigoreis() {
        reposiory.getRemoteCategoreis(remoteSource ,this);
    }

    @Override
    public void getRemoteCountryMeals() {
        reposiory.getRemoteCounrtyMeals(remoteSource , this);
    }

    @Override
    public void getRemoteCountries() {
        reposiory.getRemoteCountries(remoteSource ,this);
    }

    @Override
    public void getRemoteFilterCounrtyMeal(String counrty) {
        reposiory.getRemoteCounrtyMealsFilter(remoteSource , this , counrty);
    }

    @Override
    public void getRemoteFilterCategoryMeal(String category) {
        reposiory.getRemoteCategoryMealsFilter(remoteSource , this , category);
    }

    @Override
    public void getRemoteFilterIngredientMeal(String ingredient) {
        reposiory.getRemoteIngredientMealsFilter(remoteSource , this , ingredient);
    }

    @Override
    public void getRemoteSearchMealByName(String mealName) {
        reposiory.getRemoteMealSearcByName(remoteSource , this , mealName);
    }

    @Override
    public void getRemoteMealDetailsByName(String mealName) {
        reposiory.getRemoteMealDetailsByName(remoteSource ,this , mealName);
    }

    @Override
    public void getRemoteIngredient() {
     reposiory.getRemoteIngredient(remoteSource , this);
    }

    /******************************************** RESULT ************************************/

    @Override
    public void onRMealSuccessResult(List<RandomMeal> meal) {
        homeView.displayRandoMeal(meal);
    }

    @Override
    public void onRMealFaluireResult(String Messgae) {
        homeView.displayRandomMealError(Messgae);
    }

    @Override
    public void onCategoriesSucessResult(List<Category> categories) {
        if(homeView != null ){

            homeView.displayCategory(categories);
        }
        if (searchView != null){
            searchView.viewSearchCategory(categories);

        }
    }

    @Override
    public void onCategoriesFailResult(String Message) {
        homeView.displayCategoryError(Message);
    }



    @Override
    public void onCountyMealSuccessResult(List<Meal> meals) {
        // call home view display counrty image
        System.out.println("mahmoud hamed ");
        if(meals == null){
            System.out.println(" iam null ");
        }
        System.out.println(meals.get(0).getStrMealThumb());
        homeView.displayCountryMeals(meals);

    }

    @Override
    public void onCountyMealFailResult(String message) {
        // call home view fail message
        homeView.displayCounrtyMealError(message);
    }

    @Override
    public void onCounrtySuccessResult(List<Counrty> counrties) {
        searchView.viewSearchCounrty(counrties);
    }

    @Override
    public void onCounrtyFailResult(String message) {

    }

    @Override
    public void onCountryMealFilterSucssessResult(List<Meal> meals) {
        mealsListView.viewCountryMealsByFilter(meals);
    }

    @Override
    public void onCountryMealFilterFailResult(String message) {

    }

    @Override
    public void oncategoryMealFilterFailResult(String message) {

    }

    @Override
    public void oncategoryMealFilterSucssessResult(List<Meal> categotymeal) {
        mealsListView.viewCategoryMealsByFilter(categotymeal);

    }

    @Override
    public void onIngerdeintFilterFailResult(String message) {

    }

    @Override
    public void onIngerdientFilterSucssessResult(List<Meal> ingerdients) {
        mealsListView.viewIngredientMealsByFilter(ingerdients);
    }

    @Override
    public void onIngredientSuccessResult(List<Ingredient> ingredients) {
        searchView.viewIngredient(ingredients);
    }

    @Override
    public void onIngredientFailResult(String message) {

    }

    @Override
    public void onSearchMealByNameSuccessResult(List<Meal> meals) {
        searchView.viewSearchMealBYName(meals);
    }

    @Override
    public void onSearchMealByNameFailResult(String message) {

    }

    @Override
    public void onSearchMealDetailByNameSuccessResult(List<MealDetails> mealDetails) {
        // pass data to activity which will intersted ti show it sd
        mealDetail.viewMealDetails(mealDetails);
    }

    @Override
    public void onSearchMealDetailByNameFailResult(String meassage) {

    }
}
