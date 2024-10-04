package com.example.yummy.ui.Srearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Model.Pojos.Counrty;
import com.example.yummy.R;

import java.util.List;

public class SearchCounrtyAdapter  extends RecyclerView.Adapter<SearchCounrtyAdapter.ViewHolder> {
    Context context ;
    List<Counrty> counrties ;
    OnClickListner onClickListner ;

    public SearchCounrtyAdapter(Context context , View view  , List<Counrty> counrties , OnClickListner onClickListner){
        this.context =context ;
        this.counrties =counrties ;
        this.onClickListner = onClickListner ;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

            TextView textCountry ;
        public ViewHolder(@NonNull View counrtyCard) {
            super(counrtyCard);
            textCountry = counrtyCard.findViewById(R.id.txtSrchCounrty);


        }
    }

    @NonNull
    @Override
    public SearchCounrtyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclreView, int viewType) {

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.search_counrty_layout , recyclreView,false );
        ViewHolder viewHolder = new ViewHolder(view );
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCounrtyAdapter.ViewHolder holder, int position) {
        holder.textCountry.setText(counrties.get(position).getStrArea());

        holder.textCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onCountryClick(counrties.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return counrties.size();
    }

    public void setCounrtiesList(List<Counrty> counrties){
            this.counrties = counrties ;
    }
}
