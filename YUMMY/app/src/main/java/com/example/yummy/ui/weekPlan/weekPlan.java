package com.example.yummy.ui.weekPlan;

import static com.example.yummy.ui.home.HomeFragment.MealKey;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;
import com.example.yummy.MealPresenter.LocalDataPresenter;
import com.example.yummy.Model.DataBase.DataBase;
import com.example.yummy.Model.Pojos.MealPlan;
import com.example.yummy.databinding.FragmentWeekPlanBinding;
import com.example.yummy.ui.Details.MealDetailsActivity;


import java.util.ArrayList;
import java.util.List;


public class weekPlan extends Fragment implements planCLickListner {

    private LocalDataPresenter localDataPresenter;
    private Observer<List<MealPlan>> mealPlanObserver;
    private FragmentWeekPlanBinding binding;
    private String date ;
    public static final String fromSrc = "fromFragment" ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWeekPlanBinding.inflate(inflater , container , false) ;
        View root = binding.getRoot() ;
        // Inflate the layout for this fragment
        return root ;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        /*layuour manager for every recycler view */
        binding.rvMealOnDay.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        /*refrenc on local data presenter to request days meals from data base*/
        localDataPresenter = new LocalDataPresenter(DataBase.getInstance(getContext()).getWeekPlanDao());
        /*create an adapter to control view of every card elemts*/
        WeekPlanAdapter weekPlanAdapter = new WeekPlanAdapter(getContext(), new ArrayList<MealPlan>(), this);
        binding.rvMealOnDay.setAdapter(weekPlanAdapter);

        binding.calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                date= (String.format("%02d",day )+"-"+ String.format("%02d", (month+1))+"-"+ year );
                LiveData<List<MealPlan>> liveData = localDataPresenter.getMealsFromPlanByDay(date);
                liveData.observe(getViewLifecycleOwner() ,mealPlans->{
                    if (mealPlans != null && !mealPlans.isEmpty()) {
                        weekPlanAdapter.setPlanMealsList(mealPlans);
                        weekPlanAdapter.notifyDataSetChanged();
                    } else{
                        weekPlanAdapter.setPlanMealsList(new ArrayList<MealPlan>());
                        weekPlanAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


   }


   /*   dialog to remove  meal from plan */
    private void showAlertDialog(MealPlan mealPlan){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()) ;
        builder.setTitle("Alert");
        builder.setMessage(" Remove Meal From Plan ") ;
        builder.setCancelable(false);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                localDataPresenter.deleteMealFromWeekPlan(mealPlan);
                Toast.makeText(getContext(), "Meal removed from week plan", Toast.LENGTH_SHORT).show();
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

    @Override
    public void removeMealFromPlan(MealPlan mealPlan) {
        /* show dialog */
        showAlertDialog(mealPlan);
    }

    @Override
    public void showPlanMealDetails(String MealName) {
        /* transaction to the details */
        Intent toMealDetailsListIntent = new Intent(getContext() , MealDetailsActivity.class) ;
        toMealDetailsListIntent.putExtra(MealKey, MealName) ;
        toMealDetailsListIntent.putExtra(fromSrc, "plan") ;
        this.startActivity(toMealDetailsListIntent);
    }
}