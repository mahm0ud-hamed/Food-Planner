package com.example.yummy.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yummy.Model.Pojos.Meal;
import com.example.yummy.R;
import com.example.yummy.ui.Details.onMealClickListener;

import java.util.List;

public class HomeCountryAdapter extends RecyclerView.Adapter<HomeCountryAdapter.ViewHolder> {
    Context context ;
    List<Meal> meals;
    com.example.yummy.ui.Details.onMealClickListener onMealClickListener;
    
    public HomeCountryAdapter(Context context , View recyclerView , List<Meal> meals, onMealClickListener onMealClickListener){
        this.context = context ;
        this.meals = meals;
        this.onMealClickListener = onMealClickListener ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgMeal;
        TextView txtMealName;

        public ViewHolder(@NonNull View categoryCard) {
            super(categoryCard);

            imgMeal = categoryCard.findViewById(R.id.imgCateg) ;
            txtMealName = categoryCard.findViewById(R.id.textCateg) ;
        }
    }
    @NonNull
    @Override
    public HomeCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.layout_category, recyclerView, false);
        ViewHolder viewHolder = new HomeCountryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCountryAdapter.ViewHolder viewHolder, int position) {

        viewHolder.txtMealName.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(250 , 200 )).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(viewHolder.imgMeal);

        viewHolder.imgMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMealClickListener.onMealClick(meals.get(position).getStrMeal());
            }
        });
    }

    public void setCounrtyMealsList(List<Meal> meals){
        this.meals = meals;
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
