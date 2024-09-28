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
import com.example.yummy.Model.Category;
import com.example.yummy.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> categories ;
    Context context;
    public CategoryAdapter(Context context , View view , List<Category > categories){
        this.categories = categories ;
        this.context= context ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCateg ;
        TextView  txtCateg ;

        public ViewHolder(@NonNull View categoryCard) {
            super(categoryCard);

            imgCateg = categoryCard.findViewById(R.id.imgCateg) ;
            txtCateg = categoryCard.findViewById(R.id.textCateg) ;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.layout_category, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.txtCateg.setText(categories.get(position).getStrCategory());

        Glide.with(context).load(categories.get(position).getStrCategoryThumb())
                .apply(new RequestOptions().override(250 , 200 )).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(viewHolder.imgCateg);

    }

    public void setCategoriesList(List<Category> categories){
        this.categories = categories ;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


}
