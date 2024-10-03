package com.example.yummy.ui.dashboard;

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
import com.example.yummy.Model.Meal;
import com.example.yummy.R;
import com.example.yummy.ui.Details.onMealClickListener;

import java.util.List;

public class FilterMealAdapter extends  RecyclerView.Adapter<FilterMealAdapter.ViewHolder> {

    Context context ;
    List<Meal> meals;
    onMealClickListener onMealClickListener ;


    public FilterMealAdapter(Context context , View recyclerView , List<Meal> meals, onMealClickListener onMealClickListener ){
        this.context = context  ;
        this.meals = meals;
        this.onMealClickListener = onMealClickListener ;
    }
    public FilterMealAdapter(Context context , View recyclerView , List<Meal> meals){
        this.context = context  ;
        this.meals = meals;
        this.onMealClickListener = onMealClickListener ;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFilterMeal ;
        TextView txtFiltrMeal ;

        public ViewHolder(@NonNull View filtermealCard) {
            super(filtermealCard);
            imgFilterMeal = filtermealCard.findViewById(R.id.ImgMealFilter);
            txtFiltrMeal = filtermealCard.findViewById(R.id.txtMealNameFilter) ;
        }
    }

    @NonNull
    @Override
    public FilterMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclreView, int viewType) {

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.lyout_meals_filter , recyclreView,false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterMealAdapter.ViewHolder holder, int position) {

            holder.txtFiltrMeal.setText(meals.get(position).getStrMeal());

            Glide.with(context).load(meals.get(position).getStrMealThumb())
                    .apply(new RequestOptions().override(250, 200)).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground).into(holder.imgFilterMeal);
            holder.imgFilterMeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onMealClickListener.onMealClick(meals.get(position).getStrMeal()) ;
                }
            });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setFilterMealList(  List<Meal> meals){
        this.meals = meals;
    }
}
