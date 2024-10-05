package com.example.yummy.ui.weekPlan;

import static com.example.yummy.ui.Details.MealDetailsActivity.friDay;
import static com.example.yummy.ui.Details.MealDetailsActivity.monDay;
import static com.example.yummy.ui.Details.MealDetailsActivity.saturDay;
import static com.example.yummy.ui.Details.MealDetailsActivity.sunDay;
import static com.example.yummy.ui.Details.MealDetailsActivity.thursDay;
import static com.example.yummy.ui.Details.MealDetailsActivity.tuesDay;
import static com.example.yummy.ui.Details.MealDetailsActivity.wednsDay;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yummy.MealPresenter.LocalDataPresenter;
import com.example.yummy.Model.DataBase.DataBase;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.MealPlan;
import com.example.yummy.R;
import com.example.yummy.databinding.FragmentWeekPlanBinding;
import com.example.yummy.ui.Favourite.FavouriteClickListner;

import java.util.ArrayList;
import java.util.List;


public class weekPlan extends Fragment implements planCLickListner {
    private RecyclerView satDayRecVw;
    private RecyclerView sunDayRecVw;
    private RecyclerView monDayRecVw;
    private RecyclerView tusDayRecVw;
    private RecyclerView wdnDayRecVw;
    private RecyclerView thusDayRecVw;
    private RecyclerView friDayRecVw;
    private LocalDataPresenter localDataPresenter;
    private Observer<List<MealPlan>> observerfri;

    private FragmentWeekPlanBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWeekPlanBinding.inflate(inflater , container , false) ;
        View root = binding.getRoot() ;
        // Inflate the layout for this fragment
        return root ;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*refrence on all recycler views for every day meals */
        satDayRecVw = view.findViewById(R.id.recycViewSatDay);
        sunDayRecVw = view.findViewById(R.id.recycViewSunDay);
        monDayRecVw = view.findViewById(R.id.recycViewMonDay);
        tusDayRecVw = view.findViewById(R.id.recycViewTusDay);
        wdnDayRecVw = view.findViewById(R.id.recycVieWedDay);
        thusDayRecVw = view.findViewById(R.id.recycViewThuDay);
        friDayRecVw = view.findViewById(R.id.recycViewFriDay);


        /*layuour manager for every recycler view */
        satDayRecVw.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        sunDayRecVw.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        monDayRecVw.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        tusDayRecVw.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        wdnDayRecVw.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        thusDayRecVw.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        friDayRecVw.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        /*refrenc on local data presenter to request days meals from data base*/
        localDataPresenter = new LocalDataPresenter(DataBase.getInstance(getContext()).getWeekPlanDao());
        /*create an adapter to control view of every card elemts*/
        WeekPlanAdapter satAdpater = new WeekPlanAdapter(getContext(), new ArrayList<MealPlan>(), this);
        satDayRecVw.setAdapter(satAdpater);

        WeekPlanAdapter sunAdapter = new WeekPlanAdapter(getContext(), new ArrayList<MealPlan>(), this);
        sunDayRecVw.setAdapter(sunAdapter);

        WeekPlanAdapter monAdpater = new WeekPlanAdapter(getContext(), new ArrayList<MealPlan>(), this);
        monDayRecVw.setAdapter(monAdpater);

        WeekPlanAdapter tusAdpater = new WeekPlanAdapter(getContext(), new ArrayList<MealPlan>(), this);
        tusDayRecVw.setAdapter(tusAdpater);

        WeekPlanAdapter wdnAdpater = new WeekPlanAdapter(getContext(), new ArrayList<MealPlan>(), this);
        wdnDayRecVw.setAdapter(wdnAdpater);

        WeekPlanAdapter thusAdpater = new WeekPlanAdapter(getContext(), new ArrayList<MealPlan>(), this);
        thusDayRecVw.setAdapter(thusAdpater);

        WeekPlanAdapter friAdpater = new WeekPlanAdapter(getContext(), new ArrayList<MealPlan>(), this);
        friDayRecVw.setAdapter(friAdpater);

        /*creating observer to observe on data come from data base*/
        LiveData<List<MealPlan>> liveDataSat = localDataPresenter.getMealsFromPlanByDay(saturDay);
        Observer<List<MealPlan>> observerSat = new Observer<List<MealPlan>>() {
            @Override
            public void onChanged(List<MealPlan> mealPlans) {
                if (mealPlans != null && !mealPlans.isEmpty()) {
                    satAdpater.setPlanMealsList(mealPlans);
                    satAdpater.notifyDataSetChanged();
                }
            }
        };
        liveDataSat.observe(getViewLifecycleOwner(), observerSat);


        /*creating observer to observe on data come from data base*/
        LiveData<List<MealPlan>> liveDataSun = localDataPresenter.getMealsFromPlanByDay(sunDay);
        Observer<List<MealPlan>> observerSun = new Observer<List<MealPlan>>() {
            @Override
            public void onChanged(List<MealPlan> mealPlans) {
                if (mealPlans != null && !mealPlans.isEmpty()) {
                    sunAdapter.setPlanMealsList(mealPlans);
                    sunAdapter.notifyDataSetChanged();
                }
            }
        };
        liveDataSun.observe(getViewLifecycleOwner(), observerSun);

        /*creating observer to observe on data come from data base*/
        LiveData<List<MealPlan>> liveDataMon = localDataPresenter.getMealsFromPlanByDay(monDay);
        Observer<List<MealPlan>> observermon = new Observer<List<MealPlan>>() {
            @Override
            public void onChanged(List<MealPlan> mealPlans) {
                if (mealPlans != null && !mealPlans.isEmpty()) {
                    monAdpater.setPlanMealsList(mealPlans);
                    monAdpater.notifyDataSetChanged();
                }
            }
        };
        liveDataMon.observe(getViewLifecycleOwner(), observermon);

        /*creating observer to observe on data come from data base*/
        LiveData<List<MealPlan>> liveDataTus = localDataPresenter.getMealsFromPlanByDay(tuesDay);
        Observer<List<MealPlan>> observertus = new Observer<List<MealPlan>>() {
            @Override
            public void onChanged(List<MealPlan> mealPlans) {
                if (mealPlans != null && !mealPlans.isEmpty()) {
                    tusAdpater.setPlanMealsList(mealPlans);
                    tusAdpater.notifyDataSetChanged();
                }
            }
        };
        liveDataTus.observe(getViewLifecycleOwner(), observertus);

        /*creating observer to observe on data come from data base*/
        LiveData<List<MealPlan>> liveDataWdns = localDataPresenter.getMealsFromPlanByDay(wednsDay);
        Observer<List<MealPlan>> observerWdns = new Observer<List<MealPlan>>() {
            @Override
            public void onChanged(List<MealPlan> mealPlans) {
                if (mealPlans != null && !mealPlans.isEmpty()) {
                    wdnAdpater.setPlanMealsList(mealPlans);
                    wdnAdpater.notifyDataSetChanged();
                }
            }
        };
        liveDataWdns.observe(getViewLifecycleOwner(), observerWdns);

        /*creating observer to observe on data come from data base*/
        LiveData<List<MealPlan>> liveDataThus = localDataPresenter.getMealsFromPlanByDay(thursDay);
        Observer<List<MealPlan>> observerthus = new Observer<List<MealPlan>>() {
            @Override
            public void onChanged(List<MealPlan> mealPlans) {
                if (mealPlans != null && !mealPlans.isEmpty()) {
                    thusAdpater.setPlanMealsList(mealPlans);
                    thusAdpater.notifyDataSetChanged();
                }
            }
        };
        liveDataThus.observe(getViewLifecycleOwner(), observerthus);

        /*creating observer to observe on data come from data base*/
        LiveData<List<MealPlan>> liveDataFri = localDataPresenter.getMealsFromPlanByDay(friDay);
        Observer<List<MealPlan>> observerfri = new Observer<List<MealPlan>>() {
            @Override
            public void onChanged(List<MealPlan> mealPlans) {
                if (mealPlans != null && !mealPlans.isEmpty()) {
                    friAdpater.setPlanMealsList(mealPlans);
                    friAdpater.notifyDataSetChanged();
                }
            }
        };
        liveDataFri.observe(getViewLifecycleOwner(), observerfri);
    }


    private void showAlertDialog(MealPlan mealPlan){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()) ;
        builder.setTitle("Alert");
        builder.setMessage(" Remove Meal From Plan ") ;
        builder.setCancelable(false);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                localDataPresenter.deleteMealFromWeekPlan(mealPlan);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create() ;
        alertDialog.show();
    }

    @Override
    public void removeMealFromPlan(MealPlan mealPlan) {

        /* show dialog */
        showAlertDialog(mealPlan);
    }

    @Override
    public void showPlanMealDetails(String MealName) {

    }
}