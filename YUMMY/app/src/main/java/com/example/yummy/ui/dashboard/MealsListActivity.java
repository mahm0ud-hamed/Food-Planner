package com.example.yummy.ui.dashboard;

import static com.example.yummy.ui.dashboard.DashboardFragment.categoryKey;
import static com.example.yummy.ui.dashboard.DashboardFragment.counrtyKey;
import static com.example.yummy.ui.dashboard.DashboardFragment.ingredientKey;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Model.CountryMeal;
import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.R;
import com.example.yummy.RandoMealPresenter.RemoteDataPresenter;

import java.util.ArrayList;
import java.util.List;

public class MealsListActivity extends AppCompatActivity implements IMealsListView{

    private static final int COLOUMN_NUMBER  =1 ;
    RecyclerView recyclerView ;
    FilterMealAdapter filterMealAdapter ;
    RemoteDataPresenter remoteDataPresenter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_list);
        Intent intent = getIntent() ;
        recyclerView = findViewById(R.id.mealsListRecycView) ;
        remoteDataPresenter =new RemoteDataPresenter(RemoteDataSource.getInstance(),this) ;

        LinearLayoutManager verticalLayoutManager = new GridLayoutManager(this  , COLOUMN_NUMBER);
        recyclerView.setLayoutManager(verticalLayoutManager);
        filterMealAdapter = new FilterMealAdapter(this , recyclerView , new ArrayList<CountryMeal>()) ;
        recyclerView.setAdapter(filterMealAdapter);

        String counrtyName = intent.getStringExtra(counrtyKey) ;
        if(counrtyName != null)
            remoteDataPresenter.getRemoteFilterCounrtyMeal(counrtyName);

        String categoryName = intent.getStringExtra(categoryKey) ;
        if(categoryName != null)
            remoteDataPresenter.getRemoteFilterIngredientMeal(categoryName);

        String ingreidentName = intent.getStringExtra(ingredientKey) ;
        if (ingreidentName != null)
            remoteDataPresenter.getRemoteFilterIngredientMeal(ingreidentName);

    }

    @Override
    public void viewCountryMealsByFilter(List<CountryMeal> filterCountryMeals) {
        filterMealAdapter.setFilterMealList(filterCountryMeals);
        filterMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewCategoryMealsByFilter(List<CountryMeal> filterCategoryMeals) {
        filterMealAdapter.setFilterMealList(filterCategoryMeals);
        filterMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewIngredientMealsByFilter(List<CountryMeal> filterIngredientMeals) {
        filterMealAdapter.setFilterMealList(filterIngredientMeals);
        filterMealAdapter.notifyDataSetChanged();
    }
}

