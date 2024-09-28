package com.example.yummy.ui.home;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.yummy.Model.Category;
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.Model.RandomMeal;
import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.R;
import com.example.yummy.RandoMealPresenter.RemoteDataPresenter;
import com.example.yummy.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeView {

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
        setImageLogo(view);
        imgRandoMeal = view.findViewById(R.id.rannMealImg);
       // imgCounrtyMeal = view.findViewById(R.id.imgCounrtyMeal) ;
        cardRamdom = view.findViewById(R.id.randomCard) ;
        txtRandName = view.findViewById(R.id.txtRandName) ;
        recyclViewCateg = view.findViewById(R.id.recyclViewCateg) ;
        recyclerViewCounrty =view.findViewById(R.id.recycCounrtyView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerViewCounrty.setLayoutManager(linearLayoutManager);
        homeCountryAdapter= new HomeCountryAdapter(getContext().getApplicationContext() , recyclerViewCounrty , new ArrayList<CountryMeal>()) ;
        recyclerViewCounrty.setAdapter(homeCountryAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext().getApplicationContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclViewCateg.setLayoutManager(linearLayoutManager1);
        categoryAdapter = new CategoryAdapter(getContext().getApplicationContext(),  recyclViewCateg,  new ArrayList<Category>()) ;
        recyclViewCateg.setAdapter(categoryAdapter);

        remoteDataPresenter.getRemoteRandomMeal();
        remoteDataPresenter.getRemoteCatigoreis();
        remoteDataPresenter.getRemoteCountryMeals();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void displayRandoMeal(List<RandomMeal> Meal) {
        Glide.with(this).load(Meal.get(0).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(imgRandoMeal);
        txtRandName.setText(Meal.get(0).getStrMeal());
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
    public void displayCountryMeals(List<CountryMeal> countryMeals) {

        homeCountryAdapter.setCounrtyMealsList(countryMeals);
        homeCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayCounrtyMealError(String Error) {
        //display country meal error from calling data
    }

    public void setImageLogo(View view){

        logoImag = view.findViewById(R.id.imgLogo) ;
        logoImag.setImageResource(R.drawable.yummylogo);
    }
}