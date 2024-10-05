package com.example.yummy.ui.Favourite;

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
import com.example.yummy.R;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private Context context ;
    private List<MealDetails> mealDetails ;
    FavouriteClickListner favouriteClickListner;

    public FavouriteAdapter (Context context , List<MealDetails> mealDetails , FavouriteClickListner favouriteClickListner){
        this.context = context ;
        this.mealDetails = mealDetails ;
        this.favouriteClickListner = favouriteClickListner;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFavMeal;
        TextView txtFavMealName;
        ImageButton btnRemoveFav ;

        public ViewHolder(@NonNull View favMealCard) {
            super(favMealCard);

            imgFavMeal = favMealCard.findViewById(R.id.imgFavMeal) ;
            txtFavMealName = favMealCard.findViewById(R.id.txtFavmealName) ;
            btnRemoveFav = favMealCard.findViewById(R.id.btnRemoveFav) ;


        }
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.favourite_layout, recyclerView, false);
        ViewHolder viewHolder = new FavouriteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtFavMealName.setText(mealDetails.get(0).getStrMeal());
        Glide.with(context).load(mealDetails.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(250 , 200 )).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(holder.imgFavMeal);

        holder.btnRemoveFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouriteClickListner.removeFavouriteMeal(mealDetails.get(0));
            }
        });
        holder.imgFavMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouriteClickListner.showFavouriteMealDetails(mealDetails.get(0).getStrMeal());
            }
        });
    }

    public void setFavMealList(List<MealDetails> mealDetails){
        this.mealDetails = mealDetails;
    }


    @Override
    public int getItemCount() {
        return mealDetails.size();
    }


}
