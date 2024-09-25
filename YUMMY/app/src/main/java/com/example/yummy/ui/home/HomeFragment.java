package com.example.yummy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yummy.MainActivity;
import com.example.yummy.Model.RandomMeal;
import com.example.yummy.Network.MealRemoteDataSourceC;
import com.example.yummy.Network.NetWorkCallBack;
import com.example.yummy.Network.RanndomMealResponse;
import com.example.yummy.R;
import com.example.yummy.RandoMealPresenter.IRandomMealPresnetr;
import com.example.yummy.RandoMealPresenter.RandomMealPresenter;
import com.example.yummy.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment implements IRandoMealView{

    private FragmentHomeBinding binding;
    ImageView randoMeal ;
    RandomMealPresenter randomMealPresnetr ;
    MealRemoteDataSourceC remoteDataSourceC ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        remoteDataSourceC= MealRemoteDataSourceC.getInstance();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
             randomMealPresnetr = new RandomMealPresenter(remoteDataSourceC, this );
            randoMeal = view.findViewById(R.id.rannMealImg);
            randomMealPresnetr.getRemoteRandomMeal();
            

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
                .error(R.drawable.ic_launcher_foreground).into(randoMeal);
        
    }

    @Override
    public void displayRandomMealError(String Error) {
       // Toast.makeText(HomeFragment.this, "Hello from error", Toast.LENGTH_SHORT).show();

    }
}