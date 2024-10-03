package com.example.yummy.ui.dashboard;

import static com.example.yummy.ui.dashboard.DashboardFragment.categoryKey;
import static com.example.yummy.ui.dashboard.DashboardFragment.counrtyKey;
import static com.example.yummy.ui.dashboard.DashboardFragment.ingredientKey;
import static com.example.yummy.ui.home.HomeFragment.MealKey;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Model.Meal;
import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.R;
import com.example.yummy.RandoMealPresenter.RemoteDataPresenter;
import com.example.yummy.ui.Details.MealDetailsActivity;
import com.example.yummy.ui.Details.onMealClickListener;

import java.util.ArrayList;
import java.util.List;

public class MealsListActivity extends AppCompatActivity implements IMealsListView , onMealClickListener {

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
        filterMealAdapter = new FilterMealAdapter(this , recyclerView , new ArrayList<Meal>(),this) ;
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
    public void viewCountryMealsByFilter(List<Meal> filterMeals) {
        filterMealAdapter.setFilterMealList(filterMeals);
        filterMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewCategoryMealsByFilter(List<Meal> filterCategoryMeals) {
        filterMealAdapter.setFilterMealList(filterCategoryMeals);
        filterMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewIngredientMealsByFilter(List<Meal> filterIngredientMeals) {
        filterMealAdapter.setFilterMealList(filterIngredientMeals);
        filterMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealClick(String MealName) {
        Intent toMealsDtailIntent = new Intent(this , MealDetailsActivity.class) ;
        toMealsDtailIntent.putExtra(MealKey, MealName) ;
        MealsListActivity.this.startActivity(toMealsDtailIntent);
    }
}

