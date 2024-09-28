package com.example.yummy.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yummy.Model.Category;
import com.example.yummy.Model.Counrty;
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<Category> categories;
    Context context ;
    OnClickListner onClickListner ;
    public SearchAdapter(Context context , View categoryCard , List<Category> categories , OnClickListner onClickListner){
        this.categories = categories ;
        this.context = context ;
        this.onClickListner = onClickListner;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView categoryImage ;
        ImageView categoryImage2;
        public ViewHolder(@NonNull View categoryCard) {
            super(categoryCard);

            categoryImage = categoryCard.findViewById(R.id.srchImage) ;
            categoryImage2= categoryCard.findViewById(R.id.srchImage2);
        }
    }
    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclreView , int viewType) {

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.search_layout , recyclreView,false );
        ViewHolder viewHolder = new ViewHolder(view );
        return viewHolder ;

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        if((position %2) == 0){
            Glide.with(context).load(categories.get(position).getStrCategoryThumb())
                    .apply(new RequestOptions().override(250 , 200 )).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground).into(holder.categoryImage);
        }
        else{
            Glide.with(context).load(categories.get(position).getStrCategoryThumb())
                    .apply(new RequestOptions().override(250, 200)).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground).into(holder.categoryImage2);
        }

        holder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onCatgeoryClick(categories.get(position).getStrCategory());
            }
        });
    }

    public void setSearchCategoriesList(List<Category > categories ){
        this.categories = categories ;
    }

    @Override
    public int getItemCount() {
        return categories.size() ;
    }
}
