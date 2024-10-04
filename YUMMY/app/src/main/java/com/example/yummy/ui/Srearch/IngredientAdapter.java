package com.example.yummy.ui.Srearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yummy.Model.Pojos.Ingredient;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private  Context context ;
    private List<Ingredient> ingredients  = null;

    private OnClickListner onClickListner ;

    private List<String> ingredientNames =null ;
    private List<String> ingredientMeasure = null ;
    public IngredientAdapter(Context context , View recyclerview , List<Ingredient> ingredients ,OnClickListner onClickListner){
        this.context = context ;
        this.ingredients = ingredients ;
        this.onClickListner = onClickListner ;
    }
    public IngredientAdapter(Context context , View recycleView , List<MealDetails> mealDetails){
        this.context = context ;
//        this.ingredientNames =  new ArrayList<>();
        this.ingredientNames = mealDetails.get(0).getAllIngredientNames() ;
        this.ingredientMeasure = mealDetails.get(0).getAllIngredientAmount() ;
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ImageIngerd ;
        TextView txt ;
        public ViewHolder(@NonNull View IngerdCard) {
            super(IngerdCard);

            ImageIngerd = IngerdCard.findViewById(R.id.srchImage) ;
            txt = IngerdCard.findViewById(R.id.txtOnImg);
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
        if (ingredients != null) {
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + ingredients.get(position).getStrIngredient().toString() + ".png")
                    .apply(new RequestOptions().override(250, 200)).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground).into(holder.ImageIngerd);


            holder.ImageIngerd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ingredients.get(position) != null) {
                        onClickListner.onIngerdientClick(ingredients.get(position).getStrIngredient());
                    } else {
                        Toast.makeText(context, "category is null ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if(ingredientNames != null){
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + ingredientNames.get(position).toString() + ".png")
                    .apply(new RequestOptions().override(250, 200)).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground).into(holder.ImageIngerd);
            holder.txt.setText(ingredientMeasure.get(position));
        }

    }

    public void setIngredientList(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }
    @Override
    public int getItemCount() {
        int size =0  ;
        if(ingredients != null)
              size =  ingredients.size();
        else{
            size = ingredientNames.size();
        }
        System.out.println("the size of ingredient is " + size);
        return  size ;
    }
}
