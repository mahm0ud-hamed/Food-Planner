package com.example.yummy.ui.home;

import static com.example.yummy.ui.Srearch.SearchFragment.ingredientKey;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummy.Model.Pojos.Category;
import com.example.yummy.Model.Pojos.Meal;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.RandomMeal;
import com.example.yummy.Model.Network.RemoteDataSource;
import com.example.yummy.R;
import com.example.yummy.MealPresenter.RemoteDataPresenter;
import com.example.yummy.databinding.FragmentHomeBinding;
import com.example.yummy.ui.Details.MealDetailsActivity;
import com.example.yummy.ui.Details.onMealClickListener;
import com.example.yummy.ui.Srearch.MealsListActivity;
import com.example.yummy.ui.Srearch.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeView , onMealClickListener ,onCategoryClickListner {
    public static final String MealKey= "mealNmae" ;
    private FragmentHomeBinding binding;
    ImageView imgRandoMeal;
    ImageView imgCategory ;
    ImageView logoImag ;
    ImageView imgCounrtyMeal;
    CardView cardRamdom;
    TextView txtRandName ;
    RemoteDataPresenter remoteDataPresenter;
    CategoryAdapter categoryAdapter ;
    RecyclerView recyclViewCateg ;
    RecyclerView recyclerViewCounrty ;
    HomeCountryAdapter homeCountryAdapter ;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*passing reomte data source to presenter without create an object of it because we are in view */
        remoteDataPresenter = new RemoteDataPresenter(RemoteDataSource.getInstance(), this );
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        remoteDataPresenter.getRemoteRandomMeal();
        remoteDataPresenter.getRemoteCatigoreis();
        remoteDataPresenter.getRemoteCountryMeals();
      //  setImageLogo(view);
        imgRandoMeal = view.findViewById(R.id.rannMealImg);
       // imgCounrtyMeal = view.findViewById(R.id.imgCounrtyMeal) ;
        cardRamdom = view.findViewById(R.id.randomCard) ;
        txtRandName = view.findViewById(R.id.txtRandName) ;

        recyclViewCateg = view.findViewById(R.id.recyclViewCateg) ;
        recyclerViewCounrty =view.findViewById(R.id.recycCounrtyView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewCounrty.setLayoutManager(linearLayoutManager);

        homeCountryAdapter= new HomeCountryAdapter(getContext().getApplicationContext() , recyclerViewCounrty , new ArrayList<Meal>(), this) ;
        recyclerViewCounrty.setAdapter(homeCountryAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext().getApplicationContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclViewCateg.setLayoutManager(linearLayoutManager1);

        categoryAdapter = new CategoryAdapter(getContext().getApplicationContext(),  recyclViewCateg,  new ArrayList<Category>() ,this) ;
        recyclViewCateg.setAdapter(categoryAdapter);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void displayRandoMeal(List<RandomMeal> meal) {
        Glide.with(this).load(meal.get(0).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(imgRandoMeal);
        txtRandName.setText(meal.get(0).getStrMeal());
        imgRandoMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMealDetailsListIntent = new Intent(getContext() , MealDetailsActivity.class) ;
                toMealDetailsListIntent.putExtra(MealKey, meal.get(0).getStrMeal()) ;
                HomeFragment.this.startActivity(toMealDetailsListIntent);
            }
        });

    }

    @Override
    public void displayRandomMealError(String Error) {
       // Toast.makeText(HomeFragment.this, "Hello from error", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void displayCategory(List<Category> categories) {
        categoryAdapter.setCategoriesList(categories);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayCategoryError(String Error) {
        /// display category error that return from category call

    }

    @Override
    public void displayCountryMeals(List<Meal> meals) {

        homeCountryAdapter.setCounrtyMealsList(meals);
        homeCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayCounrtyMealError(String Error) {
        //display country meal error from calling data
    }

    @Override
    public void onMealClick(String MealName) {
        Intent toMealDetailsListIntent = new Intent(getContext() , MealDetailsActivity.class) ;
        toMealDetailsListIntent.putExtra(MealKey, MealName) ;
        HomeFragment.this.startActivity(toMealDetailsListIntent);
    }

    @Override
    public void onBtnFavouriteCLickListner(MealDetails mealDetails) {

            /*adding random meal to favourite from out of its details*/
    }

    @Override
    public void onIngerdientClick(String ingredientName) {
        Intent toMealsListIntent = new Intent(getContext() , MealsListActivity.class) ;
        toMealsListIntent.putExtra(ingredientKey, ingredientName) ;
        HomeFragment.this.startActivity(toMealsListIntent);

    }
}