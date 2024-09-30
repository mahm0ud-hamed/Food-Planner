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
import com.example.yummy.Model.Counrty;
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.R;
import com.google.android.material.search.SearchBar;

import java.util.List;

public class FilterMealAdapter extends  RecyclerView.Adapter<FilterMealAdapter.ViewHolder> {

    Context context ;
    List<CountryMeal> countryMeals ;


    public FilterMealAdapter(Context context , View recyclerView , List<CountryMeal> countryMeals ){
        this.context = context  ;
        this.countryMeals = countryMeals ;
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

            holder.txtFiltrMeal.setText(countryMeals.get(position).getStrMeal());

            Glide.with(context).load(countryMeals.get(position).getStrMealThumb())
                    .apply(new RequestOptions().override(250, 200)).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground).into(holder.imgFilterMeal);

    }

    @Override
    public int getItemCount() {
        return countryMeals.size();
    }

    public void setFilterMealList(  List<CountryMeal> countryMeals ){
        this.countryMeals = countryMeals ;
    }
}
