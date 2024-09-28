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
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.R;

import java.util.List;

public class HomeCountryAdapter extends RecyclerView.Adapter<HomeCountryAdapter.ViewHolder> {
    Context context ;
    List<CountryMeal> countryMeals ;
    
    public HomeCountryAdapter(Context context , View recyclerView , List<CountryMeal> countryMeals){
        this.context = context ;
        this.countryMeals = countryMeals ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCounty;
        TextView txtCounrty ;

        public ViewHolder(@NonNull View categoryCard) {
            super(categoryCard);

            imgCounty = categoryCard.findViewById(R.id.imgCateg) ;
            txtCounrty = categoryCard.findViewById(R.id.textCateg) ;
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

        viewHolder.txtCounrty.setText(countryMeals.get(position).getStrMeal());
        Glide.with(context).load(countryMeals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(250 , 200 )).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(viewHolder.imgCounty);

    }

    public void setCounrtyMealsList(List<CountryMeal> countryMeals){
        this.countryMeals =countryMeals;
    }

    @Override
    public int getItemCount() {
        return countryMeals.size();
    }
}
