package com.example.yummy.ui.Favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.MealPresenter.ILocalDataPresenter;
import com.example.yummy.MealPresenter.LocalDataPresenter;
import com.example.yummy.Model.DataBase.DataBase;
import com.example.yummy.Model.DataBase.MealDao;
import com.example.yummy.Model.Pojos.Meal;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.R;
import com.example.yummy.databinding.FragmentFavouriteBinding;


import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements IFavouriteView{

    private FragmentFavouriteBinding binding;
    private RecyclerView recyclerView ;
    private ILocalDataPresenter localDataPresenter;
    FavouriteAdapter favouriteAdapter ;
    MealDao dao ;
    Observer<List<MealDetails>> observer;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView= view.findViewById(R.id.recycViewFav) ;
        localDataPresenter = new LocalDataPresenter( DataBase.getInstance(getContext().getApplicationContext()).getMealDAO());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        favouriteAdapter= new FavouriteAdapter(getContext() , new ArrayList<MealDetails>()) ;
        recyclerView.setAdapter(favouriteAdapter);
        LiveData<List<MealDetails>> liveData = localDataPresenter.getAllFavouriteMeals();
        observer =new Observer<List<MealDetails>>() {
            @Override
            public void onChanged(List<MealDetails> mealDetails) {
                favouriteAdapter.setFavMealList(mealDetails);
                favouriteAdapter.notifyDataSetChanged();

            }
        };
        liveData.observe(getViewLifecycleOwner() , observer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void viewFavouriteMeals(List<MealDetails> mealDetails) {
        // pass to adapter to list it on fragment

    }
}