package com.example.yummy.ui.Favourite;

import static com.example.yummy.ui.home.HomeFragment.MealKey;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.MealPresenter.ILocalDataPresenter;
import com.example.yummy.MealPresenter.LocalDataPresenter;
import com.example.yummy.Model.DataBase.DataBase;
import com.example.yummy.Model.DataBase.favouriteDao;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.MealPlan;
import com.example.yummy.R;
import com.example.yummy.databinding.FragmentFavouriteBinding;
import com.example.yummy.ui.Details.MealDetailsActivity;
import com.example.yummy.ui.home.HomeFragment;


import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements FavouriteClickListner {

    private FragmentFavouriteBinding binding;
    private RecyclerView recyclerView;
    private ILocalDataPresenter localDataPresenter;
    FavouriteAdapter favouriteAdapter;
    private Observer<List<MealDetails>> observer;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycViewFav);
        localDataPresenter = new LocalDataPresenter(DataBase.getInstance(getContext().getApplicationContext()).getMealDAO());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        favouriteAdapter = new FavouriteAdapter(getContext(), new ArrayList<MealDetails>(), this);
        recyclerView.setAdapter(favouriteAdapter);
        LiveData<List<MealDetails>> liveData = localDataPresenter.getAllFavouriteMeals();
        observer = new Observer<List<MealDetails>>() {
            @Override
            public void onChanged(List<MealDetails> mealDetails) {
                favouriteAdapter.setFavMealList(mealDetails);
                favouriteAdapter.notifyDataSetChanged();

            }
        };
        liveData.observe(getViewLifecycleOwner(), observer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void removeFavouriteMeal(MealDetails mealDetails) {
        System.out.println("heallo mahmoudhamed");
        showAlertDialog(mealDetails);
    }

    @Override
    public void showFavouriteMealDetails(String MealName) {
        Intent toMealDetailsListIntent = new Intent(getContext(), MealDetailsActivity.class);
        toMealDetailsListIntent.putExtra(MealKey, MealName);
        this.startActivity(toMealDetailsListIntent);
    }

    private void showAlertDialog(MealDetails mealDetails) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Alert");
        builder.setMessage(" Remove Meal From Favourite ");
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                localDataPresenter.deleteMealFromFavourite(mealDetails);
                Toast.makeText(getContext(), "Meal removed from week Favoutite", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /* do nothing */
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}