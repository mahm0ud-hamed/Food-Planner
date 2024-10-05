package com.example.yummy.ui.weekPlan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.MealPlan;
import com.example.yummy.R;
import com.example.yummy.ui.Favourite.FavouriteClickListner;

import java.util.List;

public class WeekPlanAdapter extends RecyclerView.Adapter<WeekPlanAdapter.ViewHolder> {
    Context context;
    List<MealPlan> planMeals;
    planCLickListner planClickListner ;
    public WeekPlanAdapter(Context context, List<MealPlan> planMeals, planCLickListner planClickListner) {
        this.context = context;
        this.planMeals = planMeals;
        this.planClickListner = planClickListner ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPlanMeal;
        TextView txtPlanMealName;
        ImageButton btnRemovePlan;

        public ViewHolder(@NonNull View favMealCard) {
            super(favMealCard);

            imgPlanMeal = favMealCard.findViewById(R.id.imgFavMeal);
            txtPlanMealName = favMealCard.findViewById(R.id.txtFavmealName);
            btnRemovePlan = favMealCard.findViewById(R.id.btnRemoveFav);

        }
    }

    @NonNull
    @Override
    public WeekPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.favourite_layout, recyclerView, false);
        ViewHolder viewHolder = new WeekPlanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeekPlanAdapter.ViewHolder holder, int position) {
        holder.txtPlanMealName.setText(planMeals.get(position).getStrMeal());
        System.out.println("the meal name is "+planMeals.get(position).getStrMeal());
        System.out.println("the meal day is "+planMeals.get(position).getPlanDayName());

        Glide.with(context).load((planMeals.get(position).getStrMealThumb()))
                .apply(new RequestOptions().override(250 , 200 )).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(holder.imgPlanMeal);

        holder.btnRemovePlan.setOnClickListener((View)->{
            planClickListner.removeMealFromPlan(planMeals.get(position));
        });
        holder.imgPlanMeal.setOnClickListener((View)->{
            planClickListner.showPlanMealDetails(planMeals.get(position).getStrMeal());
        });
    }

    public void setPlanMealsList(List<MealPlan> planMeals){
        this.planMeals =planMeals ;
    }

    @Override
    public int getItemCount() {
        return planMeals.size();
    }
}
