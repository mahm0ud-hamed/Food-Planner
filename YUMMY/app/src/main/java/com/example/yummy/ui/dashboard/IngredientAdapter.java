package com.example.yummy.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InsertGesture;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yummy.Model.Ingredient;
import com.example.yummy.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    Context context ;
    List<Ingredient> ingredients ;
    OnClickListner onClickListner ;

    public IngredientAdapter(Context context , View recyclerview , List<Ingredient> ingredients ,OnClickListner onClickListner){
        this.context = context ;
        this.ingredients = ingredients ;
        this.onClickListner = onClickListner ;
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ImageIngerd ;
        ImageView ImageIngerd2;
        public ViewHolder(@NonNull View IngerdCard) {
            super(IngerdCard);

            ImageIngerd = IngerdCard.findViewById(R.id.srchImage) ;
            ImageIngerd2= IngerdCard.findViewById(R.id.srchImage2);
        }
    }
    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclreView, int viewType) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.search_layout , recyclreView,false );
        ViewHolder viewHolder = new ViewHolder(view) ;
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        if((position %2) == 0){
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredients.get(position).getStrIngredient().toString()+".png")
                    .apply(new RequestOptions().override(250 , 200 )).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground).into(holder.ImageIngerd);
        }
        else{
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredients.get(position).getStrIngredient()+".png")
                    .apply(new RequestOptions().override(250 , 200 )).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground).into(holder.ImageIngerd);
        }

        holder.ImageIngerd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onIngerdientClick(ingredients.get(position).getStrIngredient());
            }
        });
        holder.ImageIngerd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onIngerdientClick(ingredients.get(position).getStrIngredient());
            }
        });


    }

    public void setIngredientList(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }
    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
