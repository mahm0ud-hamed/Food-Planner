package com.example.yummy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yummy.Model.Category;
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.Model.RandomMeal;
import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.R;
import com.example.yummy.RandoMealPresenter.RemoteDataPresenter;
import com.example.yummy.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment implements IHomeView {

    private FragmentHomeBinding binding;
    ImageView imgRandoMeal;
    ImageView imgCategory ;
    ImageView logoImag ;
    ImageView imgCounrtyMeal;
    RemoteDataPresenter remoteDataPresenter;
    RemoteDataSource remoteDataSource;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        remoteDataSource= RemoteDataSource.getInstance();
        remoteDataPresenter = new RemoteDataPresenter(remoteDataSource, this );
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setImageLogo(view);
        imgRandoMeal = view.findViewById(R.id.rannMealImg);
        imgCategory = view.findViewById(R.id.categoryImage);
        imgCounrtyMeal = view.findViewById(R.id.imgCounrtyMeal) ;

        remoteDataPresenter.getRemoteRandomMeal();
        remoteDataPresenter.getRemoteCatigoreis();
      //  remoteDataPresenter.getRemoteCountryMeals();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void displayRandoMeal(List<RandomMeal> Meal) {
        Glide.with(this).load(Meal.get(0).getStrMealThumb())
                .apply(new RequestOptions().override(400 , 400 )).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(imgRandoMeal);
    }

    @Override
    public void displayRandomMealError(String Error) {
       // Toast.makeText(HomeFragment.this, "Hello from error", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void displayCategory(List<Category> categories) {
        /// set image if category on what come from category call
        Glide.with(this).load(categories.get(0).getStrCategoryThumb())
                .apply(new RequestOptions().override(400 , 400 )).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(imgCategory);
    }

    @Override
    public void displayCategoryError(String Error) {
        /// display category error that return from category call

    }

    @Override
    public void displayCountryMeals(List<CountryMeal> countryMeals) {
        Glide.with(this).load(countryMeals.get(0).getStrCountyMealThumb())
                .apply(new RequestOptions().override(400 , 400 )).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(imgCounrtyMeal);
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