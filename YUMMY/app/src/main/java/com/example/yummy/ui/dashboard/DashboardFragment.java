package com.example.yummy.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Model.Category;
import com.example.yummy.Model.Counrty;
import com.example.yummy.Network.NetWorkCallBack;
import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.R;
import com.example.yummy.RandoMealPresenter.RemoteDataPresenter;
import com.example.yummy.Repsitory.Reposiory;
import com.example.yummy.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements ISearchView , OnClickListner{

    private FragmentSearchBinding binding;
    private SearchView searchView ;
    RecyclerView recyclerViewSrchCateg ;
    RemoteDataPresenter remoteDataPresenter;
    SearchAdapter searchAdapter ;
    RecyclerView recyclerViewSrchCounrty ;
    SearchCounrtyAdapter searchCounrtyAdapter ;
    OnClickListner onClickListner ;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.srchBar) ;
        recyclerViewSrchCateg  = view.findViewById(R.id.srchcCtegRcycView);
        recyclerViewSrchCounrty = view.findViewById(R.id.srchcCountyRcycView) ;

        remoteDataPresenter = new RemoteDataPresenter(RemoteDataSource.getInstance(), this );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewSrchCateg.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(getContext().getApplicationContext(),  recyclerViewSrchCateg,  new ArrayList<Category>()) ;
        recyclerViewSrchCateg.setAdapter(searchAdapter);

        LinearLayoutManager linearLayoutManagerCountry = new LinearLayoutManager(getContext().getApplicationContext());
        linearLayoutManagerCountry.setOrientation(RecyclerView.HORIZONTAL);

        recyclerViewSrchCounrty.setLayoutManager(linearLayoutManagerCountry);
        searchCounrtyAdapter = new SearchCounrtyAdapter(getContext().getApplicationContext() , recyclerViewSrchCounrty , new ArrayList<Counrty>() ,this) ;
        recyclerViewSrchCounrty.setAdapter(searchCounrtyAdapter);

        remoteDataPresenter.getRemoteCatigoreis();
        remoteDataPresenter.getRemoteCountries();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void viewSearchCategory(List<Category> categories) {
        searchAdapter.setSearchCategoriesList(categories);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewSearchCounrty(List<Counrty> countries) {
        searchCounrtyAdapter.setCounrtiesList(countries);
        searchCounrtyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCountryClick(Counrty counrty) {
        binding.srchBar.setVisibility(View.INVISIBLE);
    }
}